package com.hraczynski.isitworthtravellingapp.service;

import com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject.SimpleWikidataResult;

public interface WikidataService {
    SimpleWikidataResult getDataFromWikiDataById(String id, String name);
}
