package com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SimpleCostLivings {
    private String countryName;
    private String estimatedCost;
    private List<CostsPerGroup> costsPerGroup;
}
