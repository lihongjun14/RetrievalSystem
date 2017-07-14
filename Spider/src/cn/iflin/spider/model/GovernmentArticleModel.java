package cn.iflin.spider.model;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.iflin.spider.main.News;

/**
 * 政府正文模板
 * @author Jaypan
 *
 */
public class GovernmentArticleModel {
	/**
	 * 用于提取时间
	 */
	private static String getReleaseTime(String time){
		String reg = "[0-9]*-[0-9]*-[0-9]*";
		String newReleaseTime = "";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(time);
		while (matcher.find()) {
			newReleaseTime = matcher.group();
		}
		if(newReleaseTime==""){
			pattern = Pattern.compile("[0-9]*年[0-9]*月[0-9]*日");
			matcher = pattern.matcher(time);
			while (matcher.find()) {
				newReleaseTime = matcher.group();
				newReleaseTime=newReleaseTime.replace("年", "-").replace("月", "-").replace("日", "");
			}
		}
		return newReleaseTime;
	}
	
	/**
	 * 广东共青团
	 * @throws IOException 
	 */
	public static News getGdcylorg(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();;
		title = doc.select("body > div.cont_wrap.clearfix > div > div > h1").text();
		url = htmlUrl;
		contentElement = doc.select("body > div.cont_wrap.clearfix > div > div > div.readCont");
		content = contentElement.text();
		time = doc.select("body > div.cont_wrap.clearfix > div > div > div.listnewtop2").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 广东省经济和信息化委员会
	 * @throws IOException 
	 */
	public static News getGdeigovcn(String htmlUrl) throws IOException{
		News news = new News();
		String title = null,url,content,time = null;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		Elements els = doc.select("script");
		for(Element el: els) {
			Pattern pattern = Pattern.compile("<h1>[^\\s]*</h1>");
			Matcher matcher = pattern.matcher(el.toString());
			while (matcher.find()) {
				title = matcher.group();
			}
		}
		if(title!=null){
			title = title.replaceAll("<h1>", "").replaceAll("</h1>", "").replaceAll("<br>", "");
		}
		Pattern pattern = Pattern.compile("([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))");
		Matcher matcher = pattern.matcher(els.toString());
		while (matcher.find()) {
			time = matcher.group();
		}
		url = htmlUrl;
		contentElement = doc.select("div.Custom_UnionStyle");
		content = contentElement.text();
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 获取广东社科规划网 http://www.gdpplgopss.gov.cn/tzgg/
	 * @param args
	 * @throws IOException
	 */
	public static News getGdpplgopssgovcn(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("body > div.cm > div.detaila > div.title").text();
		url = htmlUrl;
		contentElement = doc.select("body > div.cm > div.detaila").select("p");
		content = contentElement.text();
		time = doc.select("body > div.cm > div.detaila > div.date").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 获取广东省科学技术厅 http://gdsta.cn/Category_28/Index.aspx
	 * @param args
	 * @throws IOException
	 */
	public static News getGdstacn(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("#sta_box > div.news_l > div.content > div.article_content > div.content_title").text();
		url = htmlUrl;
		contentElement = doc.select("#articleContnet").select("p");
		content = contentElement.text();
		time = doc.select("#sta_box > div.news_l > div.content > div.article_content > div.article_info").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 获取中国高等教育学会 http://www.hie.edu.cn/announcement_12579/index.shtml
	 * @param args
	 * @throws IOException
	 */
	public static News getHieeducn(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("body > div.main > div.page_r.right > div.mar_t_20.border.page_wz > p.page_title").text();
		url = htmlUrl;
		contentElement = doc.select("#mcontent > p:nth-child(2)");
		content = contentElement.text();
		time = doc.select("body > div.main > div.page_r.right > div.mar_t_20.border.page_wz > p.page_time").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 获取中华人民共和国文化部 http://www.mcprc.gov.cn/whzx/ggtz/
	 * @param args
	 * @throws IOException
	 */
	public static News getMcprcgovcn(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("#gkml > div.main > div.mainContainer > div.docTitleCls > p > font > strong").text();
		url = htmlUrl;
		contentElement = doc.select("#ContentRegion").select("p");
		content = contentElement.text();
		time = doc.select("#headContainer > tbody > tr:nth-child(2) > td > table > tbody > tr > td:nth-child(2) > span").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 获取科学技术司 http://www.moe.edu.cn/s78/A16/s8213/index.html
	 * @param args
	 * @throws IOException
	 */
	public static News getMoeeducn(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("#content_body > h1").text();
		url = htmlUrl;
		contentElement = doc.select("#content_body_txt > div").select("p");
		content = contentElement.text();
		time = doc.select("head > meta:nth-child(7)").toString();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 获取全国哲学社会科学规划办公室 http://www.npopss-cn.gov.cn/GB/219469/index.html
	 * @param args
	 * @throws IOException
	 */
	public static News getNpopsscngovcn(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("body > div.width960.container.clearfix > div.width920.d2_content.clearfix > div.d2_left > h1").text();
		url = htmlUrl;
		contentElement = doc.select("body > div.width960.container.clearfix > div.width920.d2_content.clearfix > div.d2_left > div").select("p");
		content = contentElement.text();
		time = doc.select("body > div.width960.container.clearfix > div.width920.d2_content.clearfix > div.d2_left > h4").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 获取国家自然科学基金委员会http://www.nsfc.gov.cn/publish/portal0/zdyjjh
	 * @param args
	 * @throws IOException
	 */
	public static News getNsfcgovcn(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("#ess_ctr433_ModuleContent > tbody > tr > td > div:nth-child(3) > div.title_xilan > h1").text();
		url = htmlUrl;
		contentElement = doc.select("#ess_ctr433_ModuleContent > tbody > tr > td > div:nth-child(3) > div.content_xilan");
		content = contentElement.text();
		time = doc.select("#ess_ctr433_ModuleContent > tbody > tr > td > div:nth-child(3) > div.line_xilan").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}

	/**
	 * 获取全国教育科学规划领导 http://onsgep.moe.edu.cn/edoas2/website7/level2.jsp?infoid=1335254343701173&location=TZGG
	 * @param args
	 * @throws IOException
	 */
	public static News getOnsgepmoeeducn(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("body > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > span > table > tbody > tr:nth-child(2) > td > p:nth-child(2) > strong > font").text();
		url = htmlUrl;
		contentElement = doc.select("body > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > span > table > tbody > tr:nth-child(2) > td > p:nth-child(6) > font > span > span > span");
		content = contentElement.text();
		time = doc.select("body > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > span > table > tbody > tr:nth-child(2) > td > p:nth-child(4) > font").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	public static void main(String[] args) throws IOException {
		getOnsgepmoeeducn("http://onsgep.moe.edu.cn/edoas2/website7/level3.jsp?infoid=1335254343701173&id=1496286428187811&location=TZGG");
		getOnsgepmoeeducn("http://onsgep.moe.edu.cn/edoas2/website7/level3.jsp?infoid=1335254343701173&id=1486953098265201&location=TZGG");
	}
	/**
	 * 获取国家民委门户网站 http://www.seac.gov.cn/col/col144/index.html
	 * @param args
	 * @throws IOException
	 */
	public static News getSeacgovcn(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("#article > tbody > tr:nth-child(1) > td > span").text();
		url = htmlUrl;
		contentElement = doc.select("#zoom").select("p");
		content = contentElement.text();
		time = doc.select("#article > tbody > tr:nth-child(2) > td > table > tbody > tr > td:nth-child(1)").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 获取教育部文件及通知 https://www.sinoss.net/guanli/tzgg/jybtz/
	 * @param args
	 * @throws IOException
	 */
	public static News getSinossnet(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("body > table > tbody > tr > td > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > h3").text();
		url = htmlUrl;
		contentElement = doc.select("body > table > tbody > tr > td > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(3) > td").select("p");
		content = contentElement.text();
		time = doc.select("body > table > tbody > tr > td > table:nth-child(3) > tbody > tr > td > table > tbody > tr:nth-child(3) > td > span > font").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
	/**
	 * 获取中国高校人文社会科学信息网,项目招标 https://www.sinoss.net/xiangmu/xmzb/
	 * @param args
	 * @throws IOException
	 */
	public static News getSinossnetZhaobiao(String htmlUrl) throws IOException{
		News news = new News();
		String title,url,content,time;
		Elements contentElement;
		Document doc = Jsoup.connect(htmlUrl).timeout(5000).get();
		title = doc.select("body > div.content02 > div.contLeft > div.textHeader > h1").text();
		url = htmlUrl;
		contentElement = doc.select("#endtext").select("p");
		content = contentElement.text();
		time = doc.select("body > div.content02 > div.contLeft > div.textHeader > p > span:nth-child(1)").text();
		time = getReleaseTime(time);
		news.setNews(url, title, time, content, contentElement);
		return news;
	}
}
