package com.hraczynski.isitworthtravellingapp.client.pojos.webresult;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta{

	@JsonProperty("src_url")
	private Object srcUrl;

	@JsonProperty("example_query")
	private String exampleQuery;

	@JsonProperty("js_callback_name")
	private String jsCallbackName;

	@JsonProperty("perl_module")
	private String perlModule;

	@JsonProperty("repo")
	private String repo;

	@JsonProperty("dev_date")
	private Object devDate;

	@JsonProperty("description")
	private String description;

	@JsonProperty("unsafe")
	private int unsafe;

	@JsonProperty("blockgroup")
	private Object blockgroup;

	@JsonProperty("src_name")
	private String srcName;

	@JsonProperty("maintainer")
	private Maintainer maintainer;

	@JsonProperty("src_domain")
	private String srcDomain;

	@JsonProperty("tab")
	private String tab;

	@JsonProperty("is_stackexchange")
	private Object isStackexchange;

	@JsonProperty("id")
	private String id;

	@JsonProperty("production_state")
	private String productionState;

	@JsonProperty("src_id")
	private int srcId;

	@JsonProperty("designer")
	private Object designer;

	@JsonProperty("dev_milestone")
	private String devMilestone;

	@JsonProperty("attribution")
	private Object attribution;

	@JsonProperty("name")
	private String name;

	@JsonProperty("producer")
	private Object producer;

	@JsonProperty("signal_from")
	private String signalFrom;

	@JsonProperty("topic")
	private List<String> topic;

	@JsonProperty("developer")
	private List<DeveloperItem> developer;

	@JsonProperty("created_date")
	private Object createdDate;

	@JsonProperty("live_date")
	private Object liveDate;

	@JsonProperty("src_options")
	private SrcOptions srcOptions;

	@JsonProperty("status")
	private String status;

	public Object getSrcUrl(){
		return srcUrl;
	}

	public String getExampleQuery(){
		return exampleQuery;
	}

	public String getJsCallbackName(){
		return jsCallbackName;
	}

	public String getPerlModule(){
		return perlModule;
	}

	public String getRepo(){
		return repo;
	}

	public Object getDevDate(){
		return devDate;
	}

	public String getDescription(){
		return description;
	}

	public int getUnsafe(){
		return unsafe;
	}

	public Object getBlockgroup(){
		return blockgroup;
	}

	public String getSrcName(){
		return srcName;
	}

	public Maintainer getMaintainer(){
		return maintainer;
	}

	public String getSrcDomain(){
		return srcDomain;
	}

	public String getTab(){
		return tab;
	}

	public Object getIsStackexchange(){
		return isStackexchange;
	}

	public String getId(){
		return id;
	}

	public String getProductionState(){
		return productionState;
	}

	public int getSrcId(){
		return srcId;
	}

	public Object getDesigner(){
		return designer;
	}

	public String getDevMilestone(){
		return devMilestone;
	}

	public Object getAttribution(){
		return attribution;
	}

	public String getName(){
		return name;
	}

	public Object getProducer(){
		return producer;
	}

	public String getSignalFrom(){
		return signalFrom;
	}

	public List<String> getTopic(){
		return topic;
	}

	public List<DeveloperItem> getDeveloper(){
		return developer;
	}

	public Object getCreatedDate(){
		return createdDate;
	}

	public Object getLiveDate(){
		return liveDate;
	}

	public SrcOptions getSrcOptions(){
		return srcOptions;
	}

	public String getStatus(){
		return status;
	}
}