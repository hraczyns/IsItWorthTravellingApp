package com.hraczynski.isitworthtravellingapp.client.api;

import com.hraczynski.isitworthtravellingapp.client.pojos.weatherforecast.WeatherForecastResponse;
import com.hraczynski.isitworthtravellingapp.utils.ApiConstants;
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

    public ResponseEntity<WeatherForecastResponse> getWeatherForecastResponseForCoords(double lon, double lat) {
        return restTemplate.exchange(
                prepareUrl(lon, lat),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                WeatherForecastResponse.class);
    }

    private String prepareUrl(double lon, double lat) {
        return ApiConstants.WEATHER_FORECAST_BASE_URL
                + "&lat=" + lat + "&lon=" + lon
                + "&appid=" + apiKey;

    }

}
