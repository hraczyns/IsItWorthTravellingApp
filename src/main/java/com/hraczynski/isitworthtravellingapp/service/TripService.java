package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimplePlaces;

import java.util.List;

public interface TripService {
    List<SimplePlaces> getPossibleTrips(double lon, double lat, int limit, String kinds);
}
