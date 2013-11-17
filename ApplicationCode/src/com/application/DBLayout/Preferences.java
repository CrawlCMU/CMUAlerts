package com.application.DBLayout;

public class Preferences {

	private String type;
	private String title;
	private String url;
	private String status;

	public Preferences(){}
	
	public Preferences(String type, String title, String url,String status) {
		this.type = type;
		this.title = title;
		this.url = url;
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString(){
		return "Type: "+type+" Title: "+title+" Url: "+url+" Status:"+status;
	}
	
	public int hashCode(){
		return url.hashCode();
	}
	
	public boolean equals(Object p){
		if(p instanceof Preferences){
			return url.equals(((Preferences) p).url);
		}
		return false;
	}

//	private enum Type{
//		FACEBOOK,
//		TWITTER,
//		ALERT,
//		CMUEVENT
//	}
//	
//	private enum Status{
//		SUBSCRIBED,
//		UNSUBSCRIBED
//	}
	
//	public static void main(String[] args){
//		Preferences p = new Preferences();
//		String url = "http://www.facebook.com";
//		p.setStatus("Subscribed");
//		p.setType("Facebook");
//		p.setUrl(url);
//		System.out.println(p);
//	}
	
}
