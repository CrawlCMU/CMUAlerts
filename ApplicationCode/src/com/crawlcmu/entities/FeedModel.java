package com.crawlcmu.entities;

public abstract class FeedModel 
{
	private String feedText;
	private String id;
	private String URL;
	
	
	
	public FeedModel(String feedText, String id, String uRL) 
	{
		super();
		this.feedText = feedText;
		this.id = id;
		URL = uRL;
	}
	public String getURL() 
	{
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getFeedText() 
	{
		return feedText;
	}
	public void setFeedText(String feedText) 
	{
		this.feedText = feedText;
	}
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	
	
}
