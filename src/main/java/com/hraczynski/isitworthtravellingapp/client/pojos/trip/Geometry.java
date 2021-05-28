package com.hraczynski.isitworthtravellingapp.client.pojos.trip;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry{

	@JsonProperty("coordinates")
	private List<Double> coordinates;

	@JsonProperty("type")
	private String type;

	public List<Double> getCoordinates(){
		return coordinates;
	}

	public String getType(){
		return type;
	}
}