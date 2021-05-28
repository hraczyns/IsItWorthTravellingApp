package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWebResult;

public interface WebResultService {
    SimpleWebResult getWebResultAboutCity(String cityName);
}
