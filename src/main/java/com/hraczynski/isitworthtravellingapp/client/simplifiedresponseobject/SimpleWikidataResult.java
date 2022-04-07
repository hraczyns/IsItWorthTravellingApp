package com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SimpleWikidataResult {
    private String shortDescription;
    private String url;
    private String title;
    private String coords;
}
