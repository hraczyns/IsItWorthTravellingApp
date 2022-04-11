package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.*;
import com.hraczynski.isitworthtravellingapp.model.Information;
import com.hraczynski.isitworthtravellingapp.service.*;
import com.hraczynski.isitworthtravellingapp.utils.CountryCodeToNameConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleIsItWorthTravellingService implements IsItWorthTravellingService {

    private final WebResultService webResultService;
    private final TripService tripService;
    private final WeatherService weatherService;
    private final GeocodingService geocodingService;
    private final CostOfLivingService costOfLivingService;

    @Override
    public Information getInformationAboutChosenTarget(double lon, double lat, int limit, String kinds) {
        SimpleGeocodeResult simpleGeocodeResult = geocodingService.getCityAndCountryByCoords(lon, lat);
        List<SimpleWeatherDailyItem> weatherInfo = weatherService.getWeatherByCoords(lon, lat);
        SimpleWebResult simpleWebResult = webResultService.getWebResultAboutCity(simpleGeocodeResult.getCity(), simpleGeocodeResult.getCountryCode());
        return new Information()
                .setPlaceName(simpleGeocodeResult.getCity())
                .setSimpleWebResult(simpleWebResult)
                .setSimpleWeatherDailyItems(weatherInfo)
                .setTimestamp(LocalDate.now());
    }

    @Override
    public List<SimpleWikidataResult> getTripPlaces(double lon, double lat, int limit, String kinds) {
        return tripService.getPossibleTrips(lon, lat, limit, kinds);
    }

    @Override
    public SimpleCostLivings getCostOfLivings(double lon, double lat) {
        SimpleGeocodeResult simpleGeocodeResult = geocodingService.getCityAndCountryByCoords(lon, lat);
        String countryCode = simpleGeocodeResult.getCountryCode();
        String country = CountryCodeToNameConverter.getCountryByCode(countryCode);
        return costOfLivingService.calculateLivingCost(country);
    }


}
