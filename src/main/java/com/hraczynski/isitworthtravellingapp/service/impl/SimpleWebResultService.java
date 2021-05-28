package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.api.WebResultApiClient;
import com.hraczynski.isitworthtravellingapp.client.pojos.webresult.RelatedTopicsItem;
import com.hraczynski.isitworthtravellingapp.client.pojos.webresult.WebResponse;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWebResult;
import com.hraczynski.isitworthtravellingapp.exceptions.EmptyWebResultException;
import com.hraczynski.isitworthtravellingapp.service.WebResultService;
import com.hraczynski.isitworthtravellingapp.utils.ApiConsts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class SimpleWebResultService implements WebResultService {
    private final WebResultApiClient webResultApiClient;

    @Override
    public SimpleWebResult getWebResultAboutCity(String cityName) {
        ResponseEntity<WebResponse> webResponse = webResultApiClient.getWebResponse(cityName);
        if (webResponse == null || webResponse.getBody() == null) {
            throw new EmptyWebResultException();
        }
        String shortDescription = webResponse.getBody().getAbstract();
        String url = extractAndBuildUrl(webResponse.getBody());

        if (shortDescription == null || "".equals(shortDescription) || ApiConsts.WEB_RESULT_BASE_URL.equals(url)) {
            ResponseEntity<WebResponse> webResponseAlt = webResultApiClient.getAlternativeWebResponse(cityName);
            if (webResponseAlt == null || webResponseAlt.getBody() == null) {
                throw new EmptyWebResultException();
            }
            return buildFromRelatedTopics(webResponseAlt.getBody());
        }
        return new SimpleWebResult()
                .setUrl(url)
                .setShortDescription(shortDescription);
    }

    private SimpleWebResult buildFromRelatedTopics(WebResponse body) {
        return body.getRelatedTopics()
                .stream()
                .filter(filterOnlyUsefulInformation())
                .map(s -> {
                    if (s.getIcon() == null || s.getText() == null) {
                        return new SimpleWebResult();
                    }
                    return new SimpleWebResult()
                            .setShortDescription(s.getText())
                            .setUrl(ApiConsts.WEB_RESULT_BASE_URL + s.getIcon().getURL());
                })
                .findFirst()
                .orElseGet(SimpleWebResult::new);
    }

    private Predicate<RelatedTopicsItem> filterOnlyUsefulInformation() {
        return s -> {
            if (s == null || s.getIcon() == null || s.getIcon().getURL() == null) {
                return false;
            }
            return !"".equals(s.getIcon().getURL())
                    && !"".equals(s.getText())
                    && (s.getIcon().getURL().endsWith(".jpg")
                    || s.getIcon().getURL().endsWith(".png"));
        };
    }

    private String extractAndBuildUrl(WebResponse body) {
        return ApiConsts.WEB_RESULT_BASE_URL + body.getImage();
    }

}
