/**
 * 
 */
package com.soullleo.spider.nba.model;

/**
 * @author Soul
 * @date Jan 3, 2013
 */
public enum Team{
	//西部
	Spurs("马刺","San Antonio","圣安东尼奥","SAS"),
	Grizzlies("灰熊","Memphis","孟菲斯","MEM"),
	Mavericks("小牛","Dallas","达拉斯","DAL"),
	Rockets("火箭","Houston","休斯顿","HOU"),
	Hornets("黄蜂","New Orleans","新奥尔良","NOK"),
	Timberwolves("森林狼","Minnesota","明尼苏达","MIN"),
	Nuggets("掘金","Denver","丹佛","DEN"),
	Jazz("爵士","Utah","犹他","UTH"),
	TrailBlazers("开拓者","Portland","波特兰","POR"),
	Thunder("雷霆","Oklahoma City","俄克拉荷马","OCT"),
	Kings("国王","Sacramento","萨克拉门托","SAC"),
	Suns("太阳","Phoenix","菲尼克斯","PHX"),
	Lakers("湖人","Los Angeles","洛杉矶","LAL"),
	Clippers("快船","Los Angeles","洛杉矶","LAC"),
	Warriors("勇士","Golden State","金州","GSW"),
	//#东部
	Heat("热火","Miami","迈阿密","MIA"),
	Magic("魔术","Orlando","奥兰多","ORL"),
	Hawks("亚特兰大","Atlanta","老鹰","ATL"),
	Wizards("奇才","Washington","华盛顿","WAS"),
	Bobcats("山猫","Charlotte","夏洛特","NOL"),
	Pistons("活塞","Detroit","底特律","DET"),
	Pacers("步行者","Indiana","印第安纳","IND"),
	Cavaliers("骑士","Cleveland","克利夫兰","CLE"),
	Bulls("公牛","Chicago","芝加哥","CHI"),
	Bucks("雄鹿","Milwaukee","密尔沃基","MIL"),
	Celtics("凯尔特人","Boston","波士顿","CEL"),
	SeventySixers("76人","Philadelphia","费城","PHI"),
	Knicks("尼克斯","New York","纽约","NYN"),
	Nets("篮网","New Jersey","新泽西","NJN"),
	Raptors("猛龙","Toronto","多伦多","TOR");
	
	private String cName;
	private String eCity;
	private String cCity;
	private String nickName;
	
	private Team(String cName, String eCity, String cCity, String nickName){
		this.cName = cName;
		this.eCity = eCity;
		this.cCity = cCity;
		this.nickName = nickName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String geteCity() {
		return eCity;
	}

	public void seteCity(String eCity) {
		this.eCity = eCity;
	}

	public String getcCity() {
		return cCity;
	}

	public void setcCity(String cCity) {
		this.cCity = cCity;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Override
	public String toString(){
		return this.cName + "," + this.eCity + "," + this.cCity + "," + this.nickName; 
	}
}
