package cn.iflin.score;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.lucene.queryparser.classic.ParseException;

import cn.iflin.spider.contentextractor.MysqlConnection;
import cn.iflin.spider.main.SpiderView;
import cn.iflin.score.MailServer;
public class Scorer {
	static SpiderView view = new SpiderView();
	private static Connection conn = MysqlConnection.getConnection();
//	public static void main(String[] args) throws IOException, ParseException {
//		HashMap<String , Double> scoreMap=GetScore.getResult("基于大数据分析的民办高校教学成本问题诊断与优化"
//				+ "高校区域大学生微博身份的精确识别方法"
//				+ "	基于Cytoscape的生物医学本体参数化SPARQL查询系统"
//				+ "基于描述逻辑本体推理的语义级中文校对方法 导师制下的个性化专业素质及能力培养模式研究与实践"
//				+ " 基于Cyber-Integrator语义平台的临床路径工作流管理方法研究"
//				+ "广东省自然科学基金项目“基于本体推理演化的财经大数据分析与预测研究”(项目编号:2016A030313386)； 全国统计科学研究项目(重点项目)“基于大数据舆情本体推理的经济趋势预测研究”(项目编号:2015LZ43)； 广东省高等学校优秀青年教师培养计划项目“面向大数据的生物通路本体知识图谱可视化研究”(YYQ2015239)资助；"
//				+ "民办高校; 大数据; 教学成本;"
//				+ "导师制; 本科教育; 个性化培养; 自主学习; 导师工作坊;");
//		
//		Iterator iter = scoreMap.entrySet().iterator();
//		while (iter.hasNext()) {
//			Map.Entry entry = (Map.Entry) iter.next();
//			String title=(String) entry.getKey();
//			double score = (double) entry.getValue();
////			System.out.println(title+"的分值为"+score);
//		}
//	}
	public static void startScore(String id,String title) throws Exception{
		Statement stmt;
    	stmt = conn.createStatement();
    	String info,email,realname;
    	ResultSet result =stmt.executeQuery("SELECT info,email,realname FROM userinformation");
    	while(result.next()){
    		info = result.getString("info");
    		email = result.getString("email");
    		realname= result.getString("realname");
    		if(info!=null){
    			if(getScore(info)){
        			MailServer.sendMail(email, id, title);
        			view.viewTitleDate("已推荐给 "+realname+"\n");
        		}
    		}
    		
		}
	}

	private static boolean getScore(String info) throws Exception, ParseException{
		double score = GetScore.getResult(info);
		if(score>=0.025){
			return true;
		}
		return false;
	}
}

