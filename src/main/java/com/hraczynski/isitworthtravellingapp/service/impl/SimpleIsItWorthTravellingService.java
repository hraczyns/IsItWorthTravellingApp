package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimplePlaces;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWeather;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWebResult;
import com.hraczynski.isitworthtravellingapp.model.Information;
import com.hraczynski.isitworthtravellingapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleIsItWorthTravellingService implements IsItWorthTravellingService {

    private final WebResultService webResultService;
    private final TripService tripService;
    private final WeatherService weatherService;

    @Override
    public Information getInformationAboutChosenTarget(double lon, double lat, int limit, String kinds) {
        SimpleWeather weatherInfo = weatherService.getWeatherByCoords(lon, lat);
        SimpleWebResult simpleWebResult = webResultService.getWebResultAboutCity(weatherInfo.getLocationName());
        List<SimplePlaces> possibleTrips = tripService.getPossibleTrips(lon, lat, limit, kinds);
        return new Information()
                .setPlaceName(weatherInfo.getLocationName())
                .setSimpleWebResult(simpleWebResult)
                .setSimpleWeather(weatherInfo)
                .setSimplePlacesList(possibleTrips);
    }

}
