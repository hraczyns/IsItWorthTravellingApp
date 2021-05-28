package com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SimpleWebResult {
    private String shortDescription;
    private String url;
}
