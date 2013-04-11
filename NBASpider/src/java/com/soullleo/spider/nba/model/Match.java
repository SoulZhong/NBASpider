/**
 * 
 */
package com.soullleo.spider.nba.model;

import java.util.Date;
import java.util.Set;

/**
 * @author Soul
 * @date Jan 3, 2013
 */
public class Match {

	private Date time;
	private Set<Team> team;
	private Set<Link> links;

	public Match(Date time, Set<Team> team, Set<Link> links) {
		this.time = time;
		this.team = team;
		this.links = links;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Set<Team> getTeam() {
		return team;
	}

	public void setTeam(Set<Team> team) {
		this.team = team;
	}

	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}

	@Override
	public String toString(){
		return this.time + ";" + this.team + ";" + this.links;
	}
}
