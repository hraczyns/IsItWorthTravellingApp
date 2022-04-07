package com.hraczynski.isitworthtravellingapp.client.pojos.trip.details;

public class PlaceDetailsResponse{
	private Preview preview;
	private String image;
	private Address address;
	private Sources sources;
	private WikipediaExtracts wikipediaExtracts;
	private String osm;
	private String otm;
	private String kinds;
	private Point point;
	private String xid;
	private String rate;
	private String name;
	private String wikipedia;
	private String wikidata;

	public Preview getPreview(){
		return preview;
	}

	public String getImage(){
		return image;
	}

	public Address getAddress(){
		return address;
	}

	public Sources getSources(){
		return sources;
	}

	public WikipediaExtracts getWikipediaExtracts(){
		return wikipediaExtracts;
	}

	public String getOsm(){
		return osm;
	}

	public String getOtm(){
		return otm;
	}

	public String getKinds(){
		return kinds;
	}

	public Point getPoint(){
		return point;
	}

	public String getXid(){
		return xid;
	}

	public String getRate(){
		return rate;
	}

	public String getName(){
		return name;
	}

	public String getWikipedia(){
		return wikipedia;
	}

	public String getWikidata(){
		return wikidata;
	}
}
