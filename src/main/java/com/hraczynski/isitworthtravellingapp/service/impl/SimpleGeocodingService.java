package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.api.GeocodingApiClient;
import com.hraczynski.isitworthtravellingapp.client.pojos.geocode.GeocodingResponseItem;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleGeocodeResult;
import com.hraczynski.isitworthtravellingapp.exceptions.LackOfInfoException;
import com.hraczynski.isitworthtravellingapp.service.GeocodingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SimpleGeocodingService implements GeocodingService {

    private final GeocodingApiClient geocodingApiClient;

    @Override
    public SimpleGeocodeResult getCityAndCountryByCoords(double lon, double lat) {
        log.info("Searching city and country by lon = {} and lat = {}", lon, lat);
        ResponseEntity<GeocodingResponseItem[]> cityNameAndCountryByCoords = geocodingApiClient.getCityNameAndCountryByCoords(lon, lat);
        if (cityNameAndCountryByCoords == null
                || cityNameAndCountryByCoords.getBody() == null
                || cityNameAndCountryByCoords.getStatusCode() != HttpStatus.OK
                || cityNameAndCountryByCoords.getBody().length == 0) {
            log.error("Not found any city and country matched by coords");
            throw new LackOfInfoException("The response doesn't contain city and country information");
        }
        return mapResponseToSimpleResult(cityNameAndCountryByCoords.getBody()[0]);
    }

    private SimpleGeocodeResult mapResponseToSimpleResult(GeocodingResponseItem body) {
        return new SimpleGeocodeResult()
                .setCity(body.getName())
                .setCountryCode(body.getCountry());
    }
}
