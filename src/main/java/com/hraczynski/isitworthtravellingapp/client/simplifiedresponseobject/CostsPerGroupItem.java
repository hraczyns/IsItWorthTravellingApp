package com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class CostsPerGroupItem {
    private String name;
    private String price;
}
