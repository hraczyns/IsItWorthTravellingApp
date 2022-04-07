package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleCostLivings;

public interface CostOfLivingService {
    SimpleCostLivings calculateLivingCost(String countryName);
}
