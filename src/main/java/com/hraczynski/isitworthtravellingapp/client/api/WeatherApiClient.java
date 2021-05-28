package com.hraczynski.isitworthtravellingapp.client.api;

import com.hraczynski.isitworthtravellingapp.client.pojos.weather.WeatherResponse;
import com.hraczynski.isitworthtravellingapp.utils.ApiConsts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiClient {

    @Value("${openweathermap-apikey}")
    private String apiKey;
    private final RestTemplate restTemplate;


    public WeatherApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<WeatherResponse> getWeatherResponseForCoords(double lon, double lat) {
        return restTemplate.exchange(
                prepareUrl(lon, lat),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                WeatherResponse.class);
    }

    private String prepareUrl(double lon, double lat) {
        return ApiConsts.WEATHER_BASE_URL
                + "?lat=" + lat + "&lon=" + lon
                + "&appid=" + apiKey;

    }

}
