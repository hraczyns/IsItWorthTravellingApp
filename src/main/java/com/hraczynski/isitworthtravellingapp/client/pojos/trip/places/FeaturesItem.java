package com.hraczynski.isitworthtravellingapp.client.pojos.trip.places;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeaturesItem{

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("id")
	private String id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("properties")
	private Properties properties;

	public Geometry getGeometry(){
		return geometry;
	}

	public String getId(){
		return id;
	}

	public String getType(){
		return type;
	}

	public Properties getProperties(){
		return properties;
	}
}