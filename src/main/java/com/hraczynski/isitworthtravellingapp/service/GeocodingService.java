package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleGeocodeResult;

public interface GeocodingService {
    SimpleGeocodeResult getCityAndCountryByCoords(double lon, double lat);
}
