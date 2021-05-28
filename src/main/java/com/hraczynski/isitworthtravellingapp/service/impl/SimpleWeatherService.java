package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.api.WeatherApiClient;
import com.hraczynski.isitworthtravellingapp.client.pojos.weather.WeatherItem;
import com.hraczynski.isitworthtravellingapp.client.pojos.weather.WeatherResponse;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWeather;
import com.hraczynski.isitworthtravellingapp.exceptions.LackOfWeatherInfoException;
import com.hraczynski.isitworthtravellingapp.service.WeatherService;
import com.hraczynski.isitworthtravellingapp.utils.ApiConsts;
import com.hraczynski.isitworthtravellingapp.utils.CelsiusConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleWeatherService implements WeatherService {
    private final WeatherApiClient weatherApiClient;

    public SimpleWeather getWeatherByName(String cityName) {
        // not implemented
        return null;
    }

    private SimpleWeather mapToSimpleWeather(WeatherResponse body) {
        WeatherItem weatherItem = body.getWeather()
                .stream()
                .findFirst()
                .orElseThrow(LackOfWeatherInfoException::new);
        String iconUrl = ApiConsts.WEATHER_ICON_URL + weatherItem.getIcon() + "@2x.png";
        return new SimpleWeather()
                .setIconUrl(iconUrl)
                .setDescription(weatherItem.getDescription())
                .setWeatherOverlook(weatherItem.getMain())
                .setTemperature(CelsiusConverter.convertFromKelvin(body.getMain().getTemp()))
                .setLocationName(body.getName());
    }

    @Override
    public SimpleWeather getWeatherByCoords(double lon, double lat) {
        ResponseEntity<WeatherResponse> weatherResponseForCity = weatherApiClient.getWeatherResponseForCoords(lon, lat);
        if (weatherResponseForCity == null || weatherResponseForCity.getBody() == null) {
            throw new LackOfWeatherInfoException();
        }
        return mapToSimpleWeather(weatherResponseForCity.getBody());
    }
}
