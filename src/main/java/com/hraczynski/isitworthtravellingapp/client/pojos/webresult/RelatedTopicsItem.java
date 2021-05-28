package com.hraczynski.isitworthtravellingapp.client.pojos.webresult;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatedTopicsItem{

	@JsonProperty("Topics")
	private List<TopicsItem> topics;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Text")
	private String text;

	@JsonProperty("Icon")
	private Icon icon;

	@JsonProperty("FirstURL")
	private String firstURL;

	@JsonProperty("Result")
	private String result;

	public List<TopicsItem> getTopics(){
		return topics;
	}

	public String getName(){
		return name;
	}

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