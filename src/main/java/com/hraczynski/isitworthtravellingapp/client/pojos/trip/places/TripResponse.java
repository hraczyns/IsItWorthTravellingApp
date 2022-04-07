package com.hraczynski.isitworthtravellingapp.client.pojos.trip.places;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TripResponse{

	@JsonProperty("features")
	private List<FeaturesItem> features;

	@JsonProperty("type")
	private String type;

	public List<FeaturesItem> getFeatures(){
		return features;
	}

	public String getType(){
		return type;
	}
}