package com.hraczynski.isitworthtravellingapp.client.pojos.webresult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopicsItem{

	@JsonProperty("Text")
	private String text;

	@JsonProperty("Icon")
	private Icon icon;

	@JsonProperty("FirstURL")
	private String firstURL;

	@JsonProperty("Result")
	private String result;

	public String getText(){
		return text;
	}

	public Icon getIcon(){
		return icon;
	}

	public String getFirstURL(){
		return firstURL;
	}

	public String getResult(){
		return result;
	}
}