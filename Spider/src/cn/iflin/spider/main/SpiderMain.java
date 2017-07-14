package cn.iflin.spider.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import cn.iflin.score.AddIndex;
import cn.iflin.score.Scorer;
import cn.iflin.spider.contentextractor.MysqlConnection;
import cn.iflin.spider.contentextractor.SpiderUrlDAO;
import cn.iflin.spider.contentextractor.TitleAnalyer;
import cn.iflin.spider.model.GovernmentArticleModel;
import cn.iflin.spider.model.GovernmentModel;
/**
 * 爬虫主函数
 * @author Jaypan
 *
 */
public class SpiderMain {
	private static Connection conn = MysqlConnection.getConnection();
	static SpiderView view = new SpiderView();
	/**
	 * 用来增加新的文章内容
	 * @param args
	 * @throws Exception
	 */
	public static void startSpider() throws Exception {
		inputUrl(GovernmentModel.getGdcylorg());
		inputUrl(GovernmentModel.getGdeigovcn());
		inputUrl(GovernmentModel.getGdpplgopssgovcn());
		inputUrl(GovernmentModel.getGdstacn());
//		//inputUrl(GovernmentModel.getGdwhtgovcn());//访问不上
		inputUrl(GovernmentModel.getHieeducn());//没有符合的文章
		inputUrl(GovernmentModel.getMcprcgovcn());
		inputUrl(GovernmentModel.getMoeeducn());
		inputUrl(GovernmentModel.getNpopsscngovcn());
		inputUrl(GovernmentModel.getNsfcgovcn());//没有符合的文章
		inputUrl(GovernmentModel.getOnsgepmoeeducn());
		inputUrl(GovernmentModel.getSeacgovcn());
		inputUrl(GovernmentModel.getSinossnet());//正文爬取可能有问题
		inputUrl(GovernmentModel.getSinossnetZhaobiao());
	}
	
	private static boolean inputUrl(ArrayList<String> list ) throws Exception{
		if(list == null){
			return false;
		}
		String source = "",url="";
		source = list.get(0);
		url = SpiderUrlDAO.getLastUrl(source);
		ArrayList<String> urlList = new ArrayList<String>();
		for(int i =1;i<list.size();i++){
			if(url.equals(list.get(i))){
				break;
			}else{
				urlList.add(list.get(i));
			}
		}
		for(int i =urlList.size();i>=1;i--){
			if(url.equals(urlList.get(i-1))){
				break;
			}else{
				if(outputMysql(urlList.get(i-1),source)){
					continue;
				}else{
					return false;
				}
				
			}
		}
		Date date = new Date();
		view.viewTitleDate(source+date+"开始\n");
//		System.out.println(source);
//		for(int i=1;i<list.size();i++){
//			outputMysql(list.get(i),source);
//		}
		return true;
	}
	
	/**
	 * 获取网址内的内容
	 * @param htmlUrl
	 * @throws Exception
	 */
	private static boolean outputMysql(String htmlUrl,String source) throws Exception{
		News news=null;
		switch (source){
			case "广东共青团": news = GovernmentArticleModel.getGdcylorg(htmlUrl); break;
			case "广东省经济和信息化委员会" :news = GovernmentArticleModel.getGdeigovcn(htmlUrl);break;
			case "广东社科规划网" :news = GovernmentArticleModel.getGdpplgopssgovcn(htmlUrl);break;
			case "广东省科学技术厅" :news = GovernmentArticleModel.getGdstacn(htmlUrl);break;
			case "中国高等教育学会" :news=GovernmentArticleModel.getHieeducn(htmlUrl);break;
			case "中华人民共和国文化部" :news=GovernmentArticleModel.getMcprcgovcn(htmlUrl);break;
			case "科学技术司" :news=GovernmentArticleModel.getMoeeducn(htmlUrl);break;
			case "全国哲学社会科学规划办公室" :news=GovernmentArticleModel.getNpopsscngovcn(htmlUrl);break;
			case "国家自然科学基金委员会" :news=GovernmentArticleModel.getNsfcgovcn(htmlUrl);break;
			case "全国教育科学规划领导" :news=GovernmentArticleModel.getOnsgepmoeeducn(htmlUrl);break;
			case "国家民委门户网站" :news=GovernmentArticleModel.getSeacgovcn(htmlUrl);break;
			case "教育部文件及通知" :news=GovernmentArticleModel.getSinossnet(htmlUrl);break;
			case "中国高校人文社会科学信息网" :news=GovernmentArticleModel.getSinossnetZhaobiao(htmlUrl);break;
		}
        if(news==null){
        	return false;
        }
        SpiderUrlDAO spiderUrlDao = new SpiderUrlDAO();
        if(TitleAnalyer.projectJudgment(news.getTitle())){
        	Statement stmt;
        	stmt = conn.createStatement();
        	String aritcleId = "";
        	spiderUrlDao.addAticle(news.getTitle(), news.getTime(), news.getContentElement().toString(),news.getContent(),htmlUrl,source);
			ResultSet result =stmt.executeQuery("SELECT * FROM context  ORDER BY AticleId DESC limit 1");
			while(result.next()){
				aritcleId = result.getString("AticleId");
			}
        	AddIndex.SystemIndex.addAllIndex(aritcleId, news.getTitle(), news.getTime(), news.getContentElement().toString(), htmlUrl, source);
        	AddIndex.ScoreIndex.addIndex(news.getContent());
        	Scorer.startScore(aritcleId, news.getTitle());
        	view.viewLogDate(news.getTitle()+"成功\n");
        }
        return true;
	}
}
