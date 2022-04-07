package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleCostLivings;
import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWikidataResult;
import com.hraczynski.isitworthtravellingapp.model.Information;

import java.util.List;

public interface IsItWorthTravellingService {
    Information getInformationAboutChosenTarget(double lon, double lat, int limit, String kinds);
    List<SimpleWikidataResult> getTripPlaces(double lon, double lat,int limit, String kinds);
    SimpleCostLivings getCostOfLivings(double lon, double lat);
}
