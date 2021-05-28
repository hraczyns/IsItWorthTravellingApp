package com.hraczynski.isitworthtravellingapp.client.pojos.webresult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Maintainer{

	@JsonProperty("github")
	private String github;

	public String getGithub(){
		return github;
	}
}