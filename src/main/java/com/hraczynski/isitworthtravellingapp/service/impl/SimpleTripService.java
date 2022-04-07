package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.api.TripApiClient;
import com.hraczynski.isitworthtravellingapp.client.pojos.trip.places.FeaturesItem;
import com.hraczynski.isitworthtravellingapp.client.pojos.trip.places.TripResponse;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWikidataResult;
import com.hraczynski.isitworthtravellingapp.exceptions.LackOfInfoException;
import com.hraczynski.isitworthtravellingapp.service.TripService;
import com.hraczynski.isitworthtravellingapp.service.WikidataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleTripService implements TripService {

    private final TripApiClient tripApiClient;
    private final WikidataService wikidataService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public List<SimpleWikidataResult> getPossibleTrips(double lon, double lat, int limit, String kinds) {
        ResponseEntity<TripResponse> placesForLocalization = tripApiClient.getPlacesForLocalization(lon, lat, limit, kinds);
        if (placesForLocalization == null || placesForLocalization.getBody() == null || placesForLocalization.getStatusCode() != HttpStatus.OK) {
            throw new LackOfInfoException("The response doesn't contain trip information");
        }

        List<SimpleWikidataResult> simpleWikidataResultList = new ArrayList<>();
        List<FeaturesItem> features = placesForLocalization.getBody().getFeatures();
        List<Callable<SimpleWikidataResult>> callableList = features.stream()
                .map(f -> (Callable<SimpleWikidataResult>) () -> wikidataService.getDataFromWikiDataById(f.getProperties().getWikidata(),f.getProperties().getName()))
                .collect(Collectors.toList());
        try {
            for (Future<SimpleWikidataResult> simpleWikidataResultFuture : executorService.invokeAll(callableList)) {
                SimpleWikidataResult simpleWikidataResult = simpleWikidataResultFuture.get();
                simpleWikidataResultList.add(simpleWikidataResult);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return simpleWikidataResultList;
    }
}
