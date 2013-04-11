/**
 * 
 */
package com.soullleo.spider.nba;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.soullleo.spider.nba.model.Link;
import com.soullleo.spider.nba.model.Match;
import com.soullleo.spider.nba.model.Team;

/**
 * @author Soul
 * @date Jan 1, 2013
 */
public class Spider {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	
	private static final String prefix = "2013年";
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebClient client = new WebClient();
		
		String url = "http://www.zhibo8.cc/index.html";
		try {
			
			client.setJavaScriptEnabled(false);
			HtmlPage page = client.getPage(url);
			
			List divs = page.getByXPath("//*[@id=\"left\"]/div");
			
			Set<Match>matches = new HashSet<Match>();
			
			if(divs != null && !divs.isEmpty()){
				int count = divs.size();
				for(int i = 0; i < count; i++){
					HtmlDivision div = (HtmlDivision) divs.get(i);
					if(!div.getAttribute("class").equals("box")){
						continue;
					}
					
					List<HtmlHeading2> h2s = (List<HtmlHeading2>) div.getByXPath("div/h2");

					Date showtime = null;
					if(h2s != null && !h2s.isEmpty()){
						try {
							showtime = sdf.parse(prefix + h2s.get(0).getTextContent().split("\\s")[0]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					List<HtmlListItem> lis = (List<HtmlListItem>) div.getByXPath("div/ul/li");
					
					for(int j = 0; j < lis.size(); j++){
						
						
						HtmlListItem li = lis.get(j);
						Set<Team> teams = TeamSupport.instance.getTeam(li.asText());
						Set<Link> links = new HashSet<Link>();

						DomNodeList<HtmlElement> anchors = li.getElementsByTagName("a");
						if(anchors != null && !anchors.isEmpty()){
							for(int k = 0; k < anchors.size(); k++){
								HtmlAnchor anchor = (HtmlAnchor) anchors.get(k);
								String content = anchor.getTextContent();
								String href = anchor.getHrefAttribute();
								if(content.contains("直播")){
									links.add(new Link(content, href));
								}
							}
						}
						if(teams != null && !teams.isEmpty()){
							Match match = new Match(showtime, teams, links);
							matches.add(match);
						}
						
					}
				}
				
			}
			
			for(Match match : matches){
				System.out.println(match);
			}
			
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
