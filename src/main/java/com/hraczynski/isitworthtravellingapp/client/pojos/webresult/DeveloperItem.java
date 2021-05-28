package com.hraczynski.isitworthtravellingapp.client.pojos.webresult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeveloperItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("type")
	private String type;

	@JsonProperty("url")
	private String url;

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}

	public String getUrl(){
		return url;
	}
}