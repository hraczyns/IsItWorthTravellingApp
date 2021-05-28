package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWeather;

public interface WeatherService {
    SimpleWeather getWeatherByName(String cityName);

    SimpleWeather getWeatherByCoords(double lat, double lon);
}
