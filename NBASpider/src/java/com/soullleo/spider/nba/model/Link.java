/**
 * 
 */
package com.soullleo.spider.nba.model;

/**
 * @author Soul
 * @date Jan 3, 2013
 */
public class Link {

	private String content;
	private String url;
	private Match match;
	
	public Link(String content, String url){
		this.content = content;
		this.url = url;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString(){
		return this.content + ":" + this.url;
	}
}
