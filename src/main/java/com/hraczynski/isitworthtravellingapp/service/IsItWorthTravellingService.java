package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.model.Information;

public interface IsItWorthTravellingService {
    Information getInformationAboutChosenTarget(double lon, double lat, int limit, String kinds);
}
