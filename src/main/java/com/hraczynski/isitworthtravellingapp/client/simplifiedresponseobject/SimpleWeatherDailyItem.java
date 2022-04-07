package com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class SimpleWeatherDailyItem {
    private String iconUrl;
    private String description;
    private String weatherOverlook;
    private double temperature;
    private LocalDate localDate;
}
