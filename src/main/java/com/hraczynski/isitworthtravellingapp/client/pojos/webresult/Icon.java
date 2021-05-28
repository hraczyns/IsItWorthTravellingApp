package com.hraczynski.isitworthtravellingapp.client.pojos.webresult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Icon{

	@JsonProperty("Height")
	private int height;

	@JsonProperty("Width")
	private int width;

	@JsonProperty("URL")
	private String uRL;

	public int getHeight(){
		return height;
	}

	public int getWidth(){
		return width;
	}

	public String getURL(){
		return uRL;
	}
}