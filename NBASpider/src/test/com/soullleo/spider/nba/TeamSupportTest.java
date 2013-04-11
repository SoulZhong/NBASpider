/**
 * 
 */
package com.soullleo.spider.nba;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.soullleo.spider.nba.model.Team;

/**
 * @author Soul
 * @date Jan 3, 2013
 */
public class TeamSupportTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getTeamTest() {
		Set<Team> teams = TeamSupport.instance.getTeam("10:30 NBA常规赛 湖人 - 掘金 新浪直播(Sinatv) 比分直播 购买篮球彩票 直播吧安卓版");
		assertTrue(teams.contains(Team.Lakers));
		assertTrue(teams.contains(Team.Nuggets));
	}

}
