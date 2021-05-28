package com.hraczynski.isitworthtravellingapp.client.api;

import com.hraczynski.isitworthtravellingapp.client.pojos.webresult.WebResponse;
import com.hraczynski.isitworthtravellingapp.utils.ApiConsts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WebResultApiClient {
    private final RestTemplate restTemplate;

    public ResponseEntity<WebResponse> getWebResponse(String cityName) {
        return restTemplate.exchange(
                prepareUrl(cityName, true),
                HttpMethod.GET,
                prepareHeader(),
                WebResponse.class
        );
    }

    public ResponseEntity<WebResponse> getAlternativeWebResponse(String cityName) {
        return restTemplate.exchange(
                prepareUrl(cityName, false),
                HttpMethod.GET,
                prepareHeader(),
                WebResponse.class
        );
    }


    private HttpEntity<String> prepareHeader() {
        MultiValueMap<String, String> multiValueMap = new HttpHeaders();
        multiValueMap.add("Accept", MediaType.ALL_VALUE);
        multiValueMap.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(multiValueMap);
    }

    private String prepareUrl(String cityName, boolean skipDisambig) {
        return ApiConsts.WEB_RESULT_BASE_URL + "?q=" + cityName + "&format=json&skip_disambig="
                + (skipDisambig ? "1" : "0");
    }
}
