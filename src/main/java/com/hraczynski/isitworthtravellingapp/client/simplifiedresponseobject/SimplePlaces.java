package com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SimplePlaces {
    private String name;
    private String kinds;
}
