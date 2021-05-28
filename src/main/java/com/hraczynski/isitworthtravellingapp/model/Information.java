package com.hraczynski.isitworthtravellingapp.model;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimplePlaces;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWeather;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWebResult;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Information {
    private String placeName;
    private SimpleWebResult simpleWebResult;
    private SimpleWeather simpleWeather;
    private List<SimplePlaces> simplePlacesList;
}

