package com.crawlcmu.entities;

public class FBFeed extends FeedModel 
{
	private boolean canLike;
	private boolean canComment;
	private boolean hasExpired;
	

	public FBFeed(String feedText, String id, String uRL) 
	{
		super(feedText, id, uRL);
	}


	public boolean isCanLike() 
	{
		return canLike;
	}


	public void setCanLike(boolean canLike) 
	{
		this.canLike = canLike;
	}


	public boolean isCanComment() 
	{
		return canComment;
	}


	public void setCanComment(boolean canComment) 
	{
		this.canComment = canComment;
	}


	public boolean isHasExpired() 
	{
		return hasExpired;
	}


	public void setHasExpired(boolean hasExpired)
	{
		this.hasExpired = hasExpired;
	}

}
