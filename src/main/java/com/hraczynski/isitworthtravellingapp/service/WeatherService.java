package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWeatherDailyItem;

import java.util.List;

public interface WeatherService {
    List<SimpleWeatherDailyItem> getWeatherByCoords(double lat, double lon);
}
