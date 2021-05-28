package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.api.TripApiClient;
import com.hraczynski.isitworthtravellingapp.client.pojos.trip.TripResponse;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimplePlaces;
import com.hraczynski.isitworthtravellingapp.exceptions.LackOfTripInfoException;
import com.hraczynski.isitworthtravellingapp.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleTripService implements TripService {

    private final TripApiClient tripApiClient;

    @Override
    public List<SimplePlaces> getPossibleTrips(double lon, double lat, int limit, String kinds) {
        ResponseEntity<TripResponse> placesForLocalization = tripApiClient.getPlacesForLocalization(lon, lat, limit, kinds);
        if (placesForLocalization == null || placesForLocalization.getBody() == null) {
            throw new LackOfTripInfoException();
        }
        return mapResponseToSimpleTrip(placesForLocalization.getBody());
    }

    public static List<SimplePlaces> mapResponseToSimpleTrip(TripResponse tripResponse) {
        return tripResponse.getFeatures().stream()
                .map(s -> new SimplePlaces()
                        .setName(s.getProperties().getName())
                        .setKinds(s.getProperties().getKinds()))
                .collect(Collectors.toList());


    }

}
