package com.shuttletimings.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class HTMLParser {
  private Elements div;
  
  public ArrayList<String> parse(String url) {  
	ArrayList<String> resultList=new ArrayList<String>();
	String result = getH1(url,"div#contentWrapper");
	if(result==null || result.length()==0)
		result = getH1(url,"div#admin_2ColLevelContent");
	resultList.add(result);
		
	result = getH2();
	resultList.add(result);
	
	result = getP();
	resultList.add(result);
	
	return resultList;
  }

  private String getH1(String url, String div) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			PatternMatch patternMatch = new PatternMatch();
			this.div = doc.select(div);
			Elements headingElem = this.div.select("h1");
			String heading = patternMatch.clean(headingElem.toString(),"h1");
			heading = patternMatch.cleanup(heading);
			return heading;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String getH2() {
		Elements heading2Elem = this.div.select("h2");
		PatternMatch patternMatch = new PatternMatch();
		String heading2 = patternMatch.clean(heading2Elem.toString(),"h2");
		heading2 = patternMatch.cleanup(heading2);
		return heading2+"\n";
	}
 
	
	private String getP(){
		
		Elements data = div.select("p");
		PatternMatch patternMatch = new PatternMatch();
		Iterator<Element> ite = data.iterator();
		StringBuilder sb = new StringBuilder();
		
		ite.next();
		String result = null;
		while(ite.hasNext()){
			String line = ite.next().toString();
			line = patternMatch.cleanup(line);
			if(line!=null && line.length()!=0)
			  result = patternMatch.clean(line, "other");
			if(result!=null)
			  sb.append(result+"\n");	
		}
		return sb.toString();
	}
	
}