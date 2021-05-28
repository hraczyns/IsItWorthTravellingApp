package com.hraczynski.isitworthtravellingapp.client.api;

import com.hraczynski.isitworthtravellingapp.client.pojos.trip.TripResponse;
import com.hraczynski.isitworthtravellingapp.utils.ApiConsts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TripApiClient {
    private static final int DEFAULT_LIMIT = 10;
    private static final String DEFAULT_KIND = "interesting_places";
    @Value("${opentripmap-apikey}")
    private String apiKey;
    private final RestTemplate restTemplate;

    public ResponseEntity<TripResponse> getPlacesForLocalization(double lon, double lat, int limit, String kinds) {
        return restTemplate.exchange(
                prepareUrl(lon, lat, limit, kinds),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                TripResponse.class);
    }

    private String prepareUrl(double lon, double lat, int limit, String kinds) {
        return ApiConsts.TRIP_BASE_URL +
                "?lon_min=" + (lon - 0.1) +
                "&lon_max=" + (lon + 0.1) +
                "&lat_min=" + (lat - 0.1) +
                "&lat_max=" + (lat + 0.1) +
                "&limit=" + (limit != 0 && limit <= 3 ? limit : DEFAULT_LIMIT) +
                "&kinds=" + (kinds != null ? kinds : DEFAULT_KIND) +
                "&apikey=" + apiKey;
    }

}
