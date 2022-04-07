package com.hraczynski.isitworthtravellingapp.service.impl;

import com.hraczynski.isitworthtravellingapp.client.api.WikidataApiClient;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWikidataResult;
import com.hraczynski.isitworthtravellingapp.exceptions.LackOfInfoException;
import com.hraczynski.isitworthtravellingapp.service.WikidataService;
import com.hraczynski.isitworthtravellingapp.utils.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleWikidataService implements WikidataService {
    private static final String ENTITIES = "entities";
    private static final String CLAIMS = "claims";
    private static final String P18 = "P18";
    private static final String P625 = "P625";
    private static final String MAIN_SNAK = "mainsnak";
    private static final String DATA_VALUE = "datavalue";
    private static final String VALUE = "value";
    private static final String SITE_LINKS = "sitelinks";
    private static final String SITE_LINKS_WIKI = "wiki";
    private static final String TITLE = "title";
    private static final String QUERY = "query";
    private static final String PAGES = "pages";
    private static final String PAGE_NOT_EXISTS = "-1";
    private static final String EXTRACT = "extract";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    private final WikidataApiClient wikidataApiClient;

    @Override
    @SuppressWarnings("unchecked")
    public SimpleWikidataResult getDataFromWikiDataById(String id, String name) {
        log.info("Searching for wikidata item by id = " + id);
        ResponseEntity<Map<String, Object>> data = wikidataApiClient.getDataFromWikiDataById(id);
        if (data == null || data.getBody() == null || data.getStatusCode() != HttpStatus.OK || data.getBody().get(ENTITIES) == null) {
            log.error("Cannot find any data about item with id = {}", id);
            throw new LackOfInfoException("Cannot find any data about item with id = " + id);
        }
        Map<String, Object> entities = (Map<String, Object>) data.getBody().get(ENTITIES);
        Map<String, Object> qObject = (Map<String, Object>) entities.get(id);
        String imageFromEntity = getImageFromEntity(qObject);
        String wikiImageLink = getWikiImageLink(imageFromEntity);
        String shortDescription = getShortDescription(qObject, WikidataApiClient.WikidataLanguagePrefix.PL);
        String coords = getCoordinates(qObject);
        return new SimpleWikidataResult()
                .setUrl(wikiImageLink)
                .setShortDescription(shortDescription)
                .setTitle(name)
                .setCoords(coords);
    }

    @SuppressWarnings("unchecked")
    private String getCoordinates(Map<String, Object> qObject) {
        log.info("Searching for coordinates for item.");
        Object coords = getValueFromPContainer(qObject, P625);
        if (coords != null) {
            log.info("Found coords for item.");
            Map<String, Object> coordsMap = (Map<String, Object>) coords;
            double lat = (double) coordsMap.get(LATITUDE);
            double lon = (double) coordsMap.get(LONGITUDE);
            return lat + ";" + lon;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Object getValueFromPContainer(Map<String, Object> qObject, String pSearchValue) {
        Map<String, Object> claims = (Map<String, Object>) qObject.get(CLAIMS);
        List<Map<String, Object>> pContainer = (List<Map<String, Object>>) claims.get(pSearchValue);
        if (pContainer != null && !pContainer.isEmpty()) {
            Map<String, Object> p = pContainer.get(0);
            Map<String, Object> mainSnak = (Map<String, Object>) p.get(MAIN_SNAK);
            Map<String, Object> dataValue = (Map<String, Object>) mainSnak.get(DATA_VALUE);
            return dataValue.get(VALUE);
        }
        log.error("Not found value for {} container", pSearchValue);
        return null;
    }

    @SuppressWarnings("unchecked")
    private String getShortDescription(Map<String, Object> qObject, WikidataApiClient.WikidataLanguagePrefix prefix) {
        String pageTitle = getSiteToInvestigate(qObject, prefix);
        if (pageTitle == null && prefix != WikidataApiClient.WikidataLanguagePrefix.EN) {
            return getShortDescription(qObject, WikidataApiClient.WikidataLanguagePrefix.EN);
        } else if (pageTitle == null) {
            return null;
        }
        log.info("Found page title to investigate. Title = {}", pageTitle);
        ResponseEntity<Map<String, Object>> extractByPageTitle = wikidataApiClient.getExtractByPageTitle(pageTitle, prefix);
        if (extractByPageTitle == null || extractByPageTitle.getBody() == null || extractByPageTitle.getStatusCode() != HttpStatus.OK) {
            if (prefix != WikidataApiClient.WikidataLanguagePrefix.EN) {
                return getShortDescription(qObject, WikidataApiClient.WikidataLanguagePrefix.EN);
            } else {
                return null;
            }
        }
        Map<String, Object> query = (Map<String, Object>) extractByPageTitle.getBody().get(QUERY);
        Map<String, Object> pages = (Map<String, Object>) query.get(PAGES);
        Iterator<Map.Entry<String, Object>> iterator = pages.entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<String, Object> objectEntry = iterator.next();
            String pageId = objectEntry.getKey();
            if (PAGE_NOT_EXISTS.equals(pageId) && prefix != WikidataApiClient.WikidataLanguagePrefix.EN) {
                return getShortDescription(qObject, WikidataApiClient.WikidataLanguagePrefix.EN);
            }
            Map<String, Object> objectPage = (Map<String, Object>) objectEntry.getValue();
            Object extract = objectPage.get(EXTRACT);
            return extract != null ? (String) extract : null;
        }
        return null;
    }

    private String getWikiImageLink(String imageFromEntity) {
        if (imageFromEntity == null) {
            return null;
        }
        String fixedImageFromEntity = imageFromEntity.replace(" ", "_");
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(fixedImageFromEntity.getBytes());
        log.info("Image url has been successfully prepared");
        return ApiConstants.WIKIDATA_API_URL_FOR_IMAGES + "/" + md5DigestAsHex.charAt(0) + "/" + md5DigestAsHex.charAt(0) + md5DigestAsHex.charAt(1) + "/" + fixedImageFromEntity;
    }

    @SuppressWarnings("unchecked")
    private String getSiteToInvestigate(Map<String, Object> qObject, WikidataApiClient.WikidataLanguagePrefix prefix) {
        Map<String, Object> siteLinks = (Map<String, Object>) qObject.get(SITE_LINKS);
        Map<String, Object> wikiResults = (Map<String, Object>) siteLinks.get(prefix.name().toLowerCase() + SITE_LINKS_WIKI);
        if (wikiResults == null) {
            return null;
        }
        Object res = wikiResults.get(TITLE);
        return res != null ? (String) res : null;
    }

    private String getImageFromEntity(Map<String, Object> qObject) {
        Object imageVal = getValueFromPContainer(qObject, P18);
        if (imageVal != null) {
            log.info("Found image item");
            return (String) imageVal;
        }
        return null;
    }
}