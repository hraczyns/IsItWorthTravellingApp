package com.hraczynski.isitworthtravellingapp.client.api;

import com.hraczynski.isitworthtravellingapp.utils.ApiConstants;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class WikidataApiClient {
    private final RestTemplate restTemplate;

    public WikidataApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Map<String, Object>> getDataFromWikiDataById(String id) {
        return restTemplate.exchange(
                prepareUrl(id),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<>() {
                });
    }

    private String prepareUrl(String id) {
        return ApiConstants.WIKIDATA_API_URL
                + "&ids=" + id;
    }

    public ResponseEntity<Map<String, Object>> getExtractByPageTitle(String pageTitle, WikidataLanguagePrefix langPrefix) {
        try {
            return restTemplate.exchange(
                    prepareUrlForExtract(pageTitle, langPrefix.name().toLowerCase()),
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<>() {
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String prepareUrlForExtract(String pageTitle, String langPrefix) throws UnsupportedEncodingException {
        String url = ApiConstants.WIKIDATA_EXTRACTS_PARAMETERIZED.replace("<LANG_PREFIX>", langPrefix);
        return url + "&titles=" + pageTitle;
    }

    public enum WikidataLanguagePrefix {
        PL, EN
    }

}
