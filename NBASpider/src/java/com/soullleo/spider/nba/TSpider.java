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

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.soullleo.storage.thrift.Link;
import com.soullleo.storage.thrift.Match;
import com.soullleo.storage.thrift.StoreService;
import com.soullleo.storage.thrift.Team;

/**
 * @author Soul
 * @date Jan 1, 2013
 */
public class TSpider {

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
						Set<Team> teams = TeamSupport.instance.getTeamT(li.asText());
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
							Match match = new Match();
							match.setTeam(teams);
							match.setTime(showtime.getTime());
							match.setLinks(links);
									//new Match(showtime, teams, links);
							matches.add(match);
						}
						
					}
				}
				
			}
			
			for(Match match : matches){
				//System.out.println(match);
				transfer(match);
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
	
	
	
	private static void transfer(Match match){
		TTransport transport = new TSocket("127.0.0.1", 9090, 1000);
		
        // 协议要和服务端一致
        TProtocol protocol = new TBinaryProtocol.Factory().getProtocol(transport);
        StoreService.Client client = new StoreService.Client(protocol);
        try {
            transport.open();
            // 业务代码
            for (int i = 0; i < 10; ++i) {
                client.save(match);
            }
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
	}
	
	
	
	
	
	
	
	
	

}
