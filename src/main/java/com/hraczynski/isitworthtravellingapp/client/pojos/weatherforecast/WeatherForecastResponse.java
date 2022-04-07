package com.hraczynski.isitworthtravellingapp.client.pojos.weatherforecast;

import java.util.List;

public class WeatherForecastResponse {
    private String timezone;
    private int timezoneOffset;
    private List<DailyItem> daily;
    private double lon;
    private double lat;

    public String getTimezone() {
        return timezone;
    }

    public int getTimezoneOffset() {
        return timezoneOffset;
    }

    public List<DailyItem> getDaily() {
        return daily;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}