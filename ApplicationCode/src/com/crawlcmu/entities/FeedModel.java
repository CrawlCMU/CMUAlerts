package com.crawlcmu.entities;

public abstract class FeedModel 
{
	private StringBuilder feedText;
	private int id;
	private String URL;
	
	
	
	public FeedModel(StringBuilder feedText, int id, String uRL) 
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
	public StringBuilder getFeedText() 
	{
		return feedText;
	}
	public void setFeedText(StringBuilder feedText) 
	{
		this.feedText = feedText;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	
}
