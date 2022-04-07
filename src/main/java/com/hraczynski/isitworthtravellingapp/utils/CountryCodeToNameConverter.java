package com.hraczynski.isitworthtravellingapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Slf4j
public class CountryCodeToNameConverter {

    @SuppressWarnings("unchecked")
    public static String getCountryByCode(String code) {
        log.info("Searching country by code {}", code);
        File fileFromResource = getFileFromResource();
        List<Map<String, Object>> jsonMap = null;
        try {
            jsonMap = new ObjectMapper().readValue(fileFromResource, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonMap != null) {
            for (Map<String, Object> objectMap : jsonMap) {
                if (objectMap.get("code") != null && objectMap.get("code").equals(code)) {
                    String country = (String) objectMap.get("name");
                    log.info("Found country {} by its code {}", country, code);
                    return country;
                }
            }
        }
        log.error("Cannot find country by code {}", code);
        return null;
    }

    private static File getFileFromResource() {

        ClassLoader classLoader = CountryCodeToNameConverter.class.getClassLoader();
        URL resource = classLoader.getResource("json/countries.json");
        if (resource == null) {
            throw new IllegalArgumentException("File not found! Countries.json");
        } else {
            try {
                return new File(resource.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
