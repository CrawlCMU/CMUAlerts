package com.rssreader.rssdataitem;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssHandler extends DefaultHandler{

	private List<RssItem> rssList;
	
	private RssItem currentItem;
	
	private boolean ifParsingTitle;
	private boolean ifParsingLink;
	
	public RssHandler(){
		rssList = new ArrayList<RssItem>();
	}
	
	public List<RssItem> getItems(){
		return rssList;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if(qName.equals("item"))
			currentItem = new RssItem();
		else if (qName.equals("title"))
			ifParsingTitle = true;
		else if (qName.equals("link"))
			ifParsingLink = true;
	}

	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if(qName.equals("item")){
			rssList.add(currentItem);
			currentItem = null;
		}
		else if (qName.equals("title"))
			ifParsingTitle = false;
		else if (qName.equals("link"))
			ifParsingLink = false;
	}
	
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(ifParsingTitle){
			if(currentItem!=null)
				currentItem.setTitle(new String(ch,start,length));
		}
		else if(ifParsingLink){
			if(currentItem!=null){
				currentItem.setLink(new String(ch,start,length));
				ifParsingLink = false;
			}
		}
	}

}
