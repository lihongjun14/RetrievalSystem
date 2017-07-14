package cn.iflin.spider.model;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 政府网站爬取模板
 * @author Jaypan
 *
 */
public class GovernmentModel {
		public static ArrayList<String> urlList;

		/**
		 * 适用于 文章列表以 http://www.gdei.gov.cn/***开头
		 * @param url
		 * @return http://***.***.**
		 */
		private static String getNewUrl(String url){
			String reg = "[a-zA-z]+://[^\\s/]*";
			String newUrl = "";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(url);
			while (matcher.find()) {
				newUrl = matcher.group();
			}
			return newUrl;
		}
		/**
		 * 适用于切割<a href="***">
		 */
		private static String getNewHref(String hrefUrl){
			String reg = "href=\"([^\\s]*)\"";
			String newHrefUrl = "";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(hrefUrl);
			while (matcher.find()) {
				newHrefUrl = matcher.group();
			}
			return newHrefUrl;
		}
		/**
		 * 验证是否为正确的url
		 */
		private static boolean checkUrl(String url ){
			String reg = "[a-zA-z]+://[^\\s]*";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(url);
			return matcher.matches();
		}
		/**
		 * 获取广东省经济和信息化委员会
		 * http://www.gdei.gov.cn/zwgk/tzgg/index.htm
		 * @throws IOException 
		 */
		public static ArrayList<String> getGdeigovcn() throws IOException{
			String url = "http://www.gdei.gov.cn/zwgk/tzgg/index.htm";
			String newUrl = getNewUrl(url);
			String article ="" , newHrefUrl= "";
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("广东省经济和信息化委员会");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("ul.NewsList").select("li");
			for(Element e : links){
				e = links.get(count);
				count++;
				newHrefUrl = getNewHref(e.html());
				article = newHrefUrl.replace("\"", "").replace("href=../..", newUrl);
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 获取国家自然科学基金委员会
		 * http://www.nsfc.gov.cn/publish/portal0/zdyjjh
		 * @throws IOException 
		 */
		public static ArrayList<String> getNsfcgovcn() throws IOException{
			String url = "http://www.nsfc.gov.cn/publish/portal0/zdyjjh";
			String newUrl = getNewUrl(url);
			String article ="" , newHrefUrl= "";
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("国家自然科学基金委员会");
			Document doc = null;
			doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("#ess_ctr1119_ModuleContent > tbody > tr > td > ul").select("li");
			for(Element e : links){
				e = links.get(count);
				count++;
				newHrefUrl = getNewHref(e.html());
				article = newHrefUrl.replace("\"", "").replace("href=", newUrl);
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		/**
		 * 获取广东共青团
		 * http://www.gdcyl.org/Article/ShowClass.asp?ClassID=118
		 * @throws IOException 
		 */
		public static ArrayList<String> getGdcylorg() throws IOException{
			String url = "http://www.gdcyl.org/Article/ShowClass.asp?ClassID=118";
			String article ="" , newHrefUrl= "";
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("广东共青团");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("div.zx_list.gonggaolist").select("li");
			for(Element e : links){
				e = links.get(count);
				count++;
				newHrefUrl = getNewHref(e.html());
				article = newHrefUrl.replaceAll("\"", "").replace("href=", "");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 获取广东社科规划网
		 * http://www.gdpplgopss.gov.cn/tzgg/
		 */
		public static ArrayList<String> getGdpplgopssgovcn() throws IOException{
			String url = "http://www.gdpplgopss.gov.cn/tzgg/";
			String newUrl = getNewUrl(url);
			String article ="" , newHrefUrl= "";
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("广东社科规划网");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("ul.GsTL5").select("li");
			for(Element e : links){
				e = links.get(count);
				count++;
				newHrefUrl = getNewHref(e.html());
				article = newHrefUrl.replace("href=\".",newUrl+"/tzgg").replaceAll("\"", "");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 获取国家民委门户网站
		 * http://www.seac.gov.cn/col/col144/index.html
		 */
		public static ArrayList<String> getSeacgovcn() throws IOException{
			String url = "http://www.seac.gov.cn/col/col144/index.html";
			String newUrl = getNewUrl(url);
			String article ="" , newHrefUrl= "";
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("国家民委门户网站");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("div#2258").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article = newUrl+e.attr("href");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 获取全国教育科学规划领导
		 * http://onsgep.moe.edu.cn/edoas2/website7/level2.jsp?infoid=1335254343701173&location=TZGG
		 */
		public static ArrayList<String> getOnsgepmoeeducn() throws IOException{
			String url = "http://onsgep.moe.edu.cn/edoas2/website7/level2.jsp?infoid=1335254343701173&location=TZGG";
			String newUrl = getNewUrl(url);
			String article ="" , newHrefUrl= "";
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("全国教育科学规划领导");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("body > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(3) > "
					+ "td > table > tbody > tr:nth-child(2) > td > table > tbody").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article = newUrl+"/edoas2/website7/"+e.attr("href");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 获取全国哲学社会科学规划办公室
		 * http://www.npopss-cn.gov.cn/GB/219469/index.html
		 */
		public static ArrayList<String> getNpopsscngovcn() throws IOException{
			String url = "http://www.npopss-cn.gov.cn/GB/219469/index.html";
			String newUrl = getNewUrl(url);
			String article ="" , newHrefUrl= "";
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("全国哲学社会科学规划办公室");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("body > div.width960.container.clearfix > "
					+ "div.width920.d2_content.clearfix > div.d2_left > ul:nth-child(3)").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article = newUrl+e.attr("href");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		
		/**
		 * 获取教育部文件及通知
		 * https://www.sinoss.net/guanli/tzgg/jybtz/
		 */
		public static ArrayList<String> getSinossnet() throws IOException{
			String url = "https://www.sinoss.net/guanli/tzgg/jybtz/";
			String newUrl = getNewUrl(url);
			String article ="" , newHrefUrl= "";
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("教育部文件及通知");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("body > div.content02 > div.contLeft > div.listCont > ul").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article = newUrl+"/"+e.attr("href");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 获取科学技术司
		 * http://www.moe.edu.cn/s78/A16/s8213/index.html
		 */
		public static ArrayList<String> getMoeeducn() throws IOException{
			String url = "http://www.moe.edu.cn/s78/A16/s8213/index.html";
			String newUrl = getNewUrl(url);
			String article ="" ;
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("科学技术司");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("#wcmpagehtml > dl").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article = e.attr("href").replace("./", newUrl+"/s78/A16/s8213/");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 获取广东省科学技术厅
		 * http://gdsta.cn/Category_28/Index.aspx
		 */
		public static ArrayList<String> getGdstacn() throws IOException{
			String url = "http://gdsta.cn/Category_28/Index.aspx";
			String newUrl = getNewUrl(url);
			String article ="" ;
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("广东省科学技术厅");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("#sta_box > div.news_l > div.tzgg_new > ul").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article =newUrl + e.attr("href");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 获取中华人民共和国文化部
		 * http://www.mcprc.gov.cn/whzx/ggtz/
		 */
		public static ArrayList<String> getMcprcgovcn() throws IOException{
			String url = "http://www.mcprc.gov.cn/whzx/ggtz/";
			String article ="" ;
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("中华人民共和国文化部");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("body > table:nth-child(7) > tbody > tr > td:nth-child(3) "
					+ "> table:nth-child(5) > tbody > tr > td:nth-child(2)").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article =e.attr("href");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 中国高等教育学会
		 * http://www.hie.edu.cn/announcement_12579/index.shtml
		 */
		public static ArrayList<String> getHieeducn() throws IOException{
			String url = "http://www.hie.edu.cn/announcement_12579/index.shtml";
			String article ="" ;
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("中国高等教育学会");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("body > div.main > div.list_r.right.border").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article =e.attr("href");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 中国高校人文社会科学信息网,项目招标
		 *  https://www.sinoss.net/xiangmu/xmzb/
		 */
		public static ArrayList<String> getSinossnetZhaobiao() throws IOException{
			String url = "https://www.sinoss.net/xiangmu/xmzb/";
			String article ="" ;
			String newUrl = getNewUrl(url);
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("中国高校人文社会科学信息网");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("body > div.content02 > div.contLeft > div.listCont > ul").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article =newUrl+"/"+e.attr("href");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		
		/**
		 * 广东省文化厅公众服务网
		 *  http://www.gdwht.gov.cn/plus/list.php?tid=122
		 */
		public static ArrayList<String> getGdwhtgovcn() throws IOException{
			String url = "http://www.gdwht.gov.cn/plus/list.php?tid=122";
			String article ="" ;
			String newUrl = getNewUrl(url);
			int count = 0;
			urlList = new ArrayList<String>();
			urlList.add("广东省文化厅公众服务网");
			Document doc = Jsoup.connect(url).timeout(5000).get();
			Elements links = doc.select("body > div.wal > div.fr.w725 > div > div.list2 > ul").select("a");
			for(Element e : links){
				e = links.get(count);
				count++;
				article =newUrl+e.attr("href");
				if(checkUrl(article))
					urlList.add(article);
				else
					continue;
			}
			return urlList;
		}
		public static void main(String[] args) throws IOException {
			urlList=getGdwhtgovcn();
			for(int i =0 ; i<urlList.size();i++){
				System.out.println(urlList.get(i));
			}
		}
}
