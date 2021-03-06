package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWikidataResult;

import java.util.List;

public interface TripService {
    List<SimpleWikidataResult> getPossibleTrips(double lon, double lat, int limit, String kinds);
}
