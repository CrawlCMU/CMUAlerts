package com.application.DBLayout;

public class Preferences {

	private String url;
	private Type type;
	private Status status;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	private enum Type{
		FACEBOOK,
		TWITTER,
		ALERT,
		CMUEVENT
	}
	
	private enum Status{
		SUBSCRIBED,
		UNSUBSCRIBED
	}
}
