/**
 * 
 */
package com.soullleo.spider.nba;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.soullleo.spider.nba.model.Team;

/**
 * @author Soul
 * @date Jan 3, 2013
 */
public class TeamSupport {

	public static final TeamSupport instance = new TeamSupport();

	private static Pattern teamPattern;

	private TeamSupport() {
	}

	@SuppressWarnings("serial")
	private static Map<String, Team> teams = new HashMap<String, Team>() {
		{
			StringBuffer regex = new StringBuffer("(");

			for (Team team : Team.values()) {
				put(team.getcName(), team);
				regex.append(team.getcName()).append("|");
			}

			teamPattern = Pattern
					.compile(regex.substring(0, regex.length() - 1) + ")");
		}
	};

	public Set<Team> getTeam(String input) {
		Set<Team> teamSet = new HashSet<Team>();
		Matcher matcher = teamPattern.matcher(input);
		while (matcher.find()) {
			teamSet.add(teams.get(matcher.group(1)));
		}
		return teamSet;
	}
	
	public Set<com.soullleo.storage.thrift.Team> getTeamT(String input){

		Set<com.soullleo.storage.thrift.Team> teamSet = new HashSet<com.soullleo.storage.thrift.Team>();
		Matcher matcher = teamPattern.matcher(input);
		while (matcher.find()) {
			
			Team team = teams.get(matcher.group(1));
			com.soullleo.storage.thrift.Team teamt = new com.soullleo.storage.thrift.Team(); 
		
			teamt.setCCity(team.getcCity());
			teamt.setCName(team.getcName());
			teamt.setECity(team.geteCity());
			teamt.setNickName(team.getNickName());
			
			teamSet.add(teamt);
		}
		return teamSet;
	}

}
