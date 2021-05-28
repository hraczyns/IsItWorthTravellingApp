package com.hraczynski.isitworthtravellingapp.client.pojos.webresult;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class WebResponse{

	@JsonProperty("Entity")
	private String entity;

	@JsonProperty("DefinitionSource")
	private String definitionSource;

	@JsonProperty("Heading")
	private String heading;

	@JsonProperty("RelatedTopics")
	private List<RelatedTopicsItem> relatedTopics;

	@JsonProperty("Abstract")
	private String abstracts;

	@JsonProperty("AbstractURL")
	private String abstractURL;

	@JsonProperty("Definition")
	private String definition;

	@JsonProperty("DefinitionURL")
	private String definitionURL;

	@JsonProperty("AbstractText")
	private String abstractText;

	@JsonProperty("Redirect")
	private String redirect;

	@JsonProperty("Image")
	private String image;

	@JsonProperty("Infobox")
	private JsonNode infobox;

	@JsonProperty("Answer")
	private String answer;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("ImageIsLogo")
	private int imageIsLogo;

	@JsonProperty("ImageHeight")
	private int imageHeight;

	@JsonProperty("Results")
	private List<Object> results;

	@JsonProperty("ImageWidth")
	private int imageWidth;

	@JsonProperty("meta")
	private Meta meta;

	@JsonProperty("AbstractSource")
	private String abstractSource;

	@JsonProperty("AnswerType")
	private String answerType;

	public String getEntity(){
		return entity;
	}

	public String getDefinitionSource(){
		return definitionSource;
	}

	public String getHeading(){
		return heading;
	}

	public List<RelatedTopicsItem> getRelatedTopics(){
		return relatedTopics;
	}

	public String getAbstract(){
		return abstracts;
	}

	public String getAbstractURL(){
		return abstractURL;
	}

	public String getDefinition(){
		return definition;
	}

	public String getDefinitionURL(){
		return definitionURL;
	}

	public String getAbstractText(){
		return abstractText;
	}

	public String getRedirect(){
		return redirect;
	}

	public String getImage(){
		return image;
	}

	public JsonNode getInfobox(){
		return infobox;
	}

	public String getAnswer(){
		return answer;
	}

	public String getType(){
		return type;
	}

	public int getImageIsLogo(){
		return imageIsLogo;
	}

	public int getImageHeight(){
		return imageHeight;
	}

	public List<Object> getResults(){
		return results;
	}

	public int getImageWidth(){
		return imageWidth;
	}

	public Meta getMeta(){
		return meta;
	}

	public String getAbstractSource(){
		return abstractSource;
	}

	public String getAnswerType(){
		return answerType;
	}
}