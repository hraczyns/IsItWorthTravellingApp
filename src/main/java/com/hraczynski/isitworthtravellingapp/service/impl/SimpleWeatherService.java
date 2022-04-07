package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.api.WeatherApiClient;
import com.hraczynski.isitworthtravellingapp.client.pojos.weatherforecast.DailyItem;
import com.hraczynski.isitworthtravellingapp.client.pojos.weatherforecast.WeatherForecastResponse;
import com.hraczynski.isitworthtravellingapp.client.pojos.weatherforecast.WeatherItem;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWeatherDailyItem;
import com.hraczynski.isitworthtravellingapp.exceptions.LackOfInfoException;
import com.hraczynski.isitworthtravellingapp.service.WeatherService;
import com.hraczynski.isitworthtravellingapp.utils.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleWeatherService implements WeatherService {
    private final WeatherApiClient weatherApiClient;

    private List<SimpleWeatherDailyItem> mapToSimpleWeather(WeatherForecastResponse body) {
        List<DailyItem> dailyItemList = body.getDaily();
        log.info("Found weather info.");
        return dailyItemList.stream()
                .map(dailyItem -> {
                    WeatherItem weatherItem = dailyItem.getWeather().get(0);
                    return new SimpleWeatherDailyItem()
                            .setLocalDate(new Timestamp(dailyItem.getDt() * 1000L).toLocalDateTime().toLocalDate())
                            .setIconUrl(ApiConstants.WEATHER_ICON_URL + weatherItem.getIcon() + "@2x.png")
                            .setTemperature(dailyItem.getTemp().getDay())
                            .setWeatherOverlook(weatherItem.getMain())
                            .setDescription(weatherItem.getDescription());

                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SimpleWeatherDailyItem> getWeatherByCoords(double lon, double lat) {
        log.info("Searching weather.");
        ResponseEntity<WeatherForecastResponse> weatherResponseForCity = weatherApiClient.getWeatherForecastResponseForCoords(lon, lat);
        if (weatherResponseForCity == null || weatherResponseForCity.getBody() == null) {
            log.error("No weather information found");
            throw new LackOfInfoException("The response does not contain weather information.");
        }
        return mapToSimpleWeather(weatherResponseForCity.getBody());
    }
}
