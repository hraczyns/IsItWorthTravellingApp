package com.hraczynski.isitworthtravellingapp.client.pojos.webresult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SrcOptions{

	@JsonProperty("src_info")
	private String srcInfo;

	@JsonProperty("is_mediawiki")
	private int isMediawiki;

	@JsonProperty("is_wikipedia")
	private int isWikipedia;

	@JsonProperty("skip_abstract_paren")
	private int skipAbstractParen;

	@JsonProperty("language")
	private String language;

	@JsonProperty("directory")
	private String directory;

	@JsonProperty("skip_icon")
	private int skipIcon;

	@JsonProperty("is_fanon")
	private int isFanon;

	@JsonProperty("source_skip")
	private String sourceSkip;

	@JsonProperty("min_abstract_length")
	private String minAbstractLength;

	@JsonProperty("skip_image_name")
	private int skipImageName;

	@JsonProperty("skip_abstract")
	private int skipAbstract;

	@JsonProperty("skip_end")
	private String skipEnd;

	@JsonProperty("skip_qr")
	private String skipQr;

	public String getSrcInfo(){
		return srcInfo;
	}

	public int getIsMediawiki(){
		return isMediawiki;
	}

	public int getIsWikipedia(){
		return isWikipedia;
	}

	public int getSkipAbstractParen(){
		return skipAbstractParen;
	}

	public String getLanguage(){
		return language;
	}

	public String getDirectory(){
		return directory;
	}

	public int getSkipIcon(){
		return skipIcon;
	}

	public int getIsFanon(){
		return isFanon;
	}

	public String getSourceSkip(){
		return sourceSkip;
	}

	public String getMinAbstractLength(){
		return minAbstractLength;
	}

	public int getSkipImageName(){
		return skipImageName;
	}

	public int getSkipAbstract(){
		return skipAbstract;
	}

	public String getSkipEnd(){
		return skipEnd;
	}

	public String getSkipQr(){
		return skipQr;
	}
}