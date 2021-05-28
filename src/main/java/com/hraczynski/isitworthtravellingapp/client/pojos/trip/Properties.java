package com.hraczynski.isitworthtravellingapp.client.pojos.trip;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties{

	@JsonProperty("xid")
	private String xid;

	@JsonProperty("rate")
	private int rate;

	@JsonProperty("name")
	private String name;

	@JsonProperty("osm")
	private String osm;

	@JsonProperty("kinds")
	private String kinds;

	@JsonProperty("wikidata")
	private String wikidata;

	public String getXid(){
		return xid;
	}

	public int getRate(){
		return rate;
	}

	public String getName(){
		return name;
	}

	public String getOsm(){
		return osm;
	}

	public String getKinds(){
		return kinds;
	}

	public String getWikidata(){
		return wikidata;
	}
}