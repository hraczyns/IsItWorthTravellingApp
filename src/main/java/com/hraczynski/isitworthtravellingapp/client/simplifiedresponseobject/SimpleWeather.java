package com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SimpleWeather {
    private String iconUrl;
    private String description;
    private String weatherOverlook;
    private double temperature;
    private String locationName;
}
