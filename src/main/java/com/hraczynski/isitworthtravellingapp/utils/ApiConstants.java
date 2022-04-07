package com.hraczynski.isitworthtravellingapp.utils;

public final class ApiConstants {
    public static final String WEATHER_FORECAST_BASE_URL = "https://api.openweathermap.org/data/2.5/onecall?exclude=current,minutely,hourly&units=metric";
    public static final String GEOCODING_BASE_URL = "http://api.openweathermap.org/geo/1.0/reverse";
    public static final String WEATHER_ICON_URL = "http://openweathermap.org/img/wn/";
    public static final String TRIP_BASE_URL = "https://api.opentripmap.com/0.1/en/places/bbox";
    public static final String WEB_RESULT_BASE_URL = "https://api.duckduckgo.com/";
    public static final String WIKIDATA_API_URL = "https://www.wikidata.org/w/api.php?action=wbgetentities&format=json&props=claims|sitelinks";
    public static final String WIKIDATA_API_URL_FOR_IMAGES = "https://upload.wikimedia.org/wikipedia/commons";
    public static final String WIKIDATA_EXTRACTS_PARAMETERIZED = "https://<LANG_PREFIX>.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1";
}
