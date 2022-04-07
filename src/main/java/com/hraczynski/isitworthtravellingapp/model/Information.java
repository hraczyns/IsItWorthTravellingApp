package com.hraczynski.isitworthtravellingapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWeatherDailyItem;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWebResult;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class Information {
    private String placeName;
    private SimpleWebResult simpleWebResult;
    private List<SimpleWeatherDailyItem> SimpleWeatherDailyItems;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
}

