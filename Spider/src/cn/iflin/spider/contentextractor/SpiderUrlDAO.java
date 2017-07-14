package cn.iflin.spider.contentextractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class SpiderUrlDAO {
	/**
	 * 获取链接
	 * @return
	 * @throws Exception
	 */
	public String getNewUrl() throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt = conn.createStatement();
		ResultSet result =stmt.executeQuery("select * from urlmanager where id=(select max(id) from urlmanager) ");
		String url = "";
		if(result.next()){
			url = result.getString("newUrl");
		}
		return url;
	}
	/**
	 * 添加新连接
	 * @param url
	 * @param id
	 * @throws Exception
	 */
	public void addNewUrl(String url,int id) throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//ͨ通过数据的连接操作数据库
		String sql = "INSERT INTO urlmanager(newUrl,UrlId) VALUES (?,?)";
		PreparedStatement preStmt=conn.prepareStatement(sql); 
		preStmt.setString(1, url);
		preStmt.setInt(2, id);
		int result=preStmt.executeUpdate();
		if(result!=1){
			System.out.println("添加失败");
		}else{
			System.out.println("添加成功");
		}
	}
	
	public void addAticle(String title,String time ,String context,String contextNoCode,String url,String source) throws Exception{
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		String sql = "INSERT INTO context(AticleId,Title,Time,Source,Context,ContextNoCode,Url) VALUES (NULL,?,?,?,?,?,?)";
		PreparedStatement preStmt=conn.prepareStatement(sql); 
		int id=0;
		preStmt.setString(1, title);
		preStmt.setString(2, time);
		preStmt.setString(3, source);
		preStmt.setString(4, context);
		preStmt.setString(5, contextNoCode);
		preStmt.setString(6, url);
		int result=preStmt.executeUpdate();
		if(result!=1){
//			System.out.println("添加失败");
		}else{
//			System.out.println("添加成功");
		}
	}
	/**
	 * 从数据库中获取到分类下的最新的一条通知 
	 * @return
	 * @throws Exception
	 */
	public static String getLastUrl(String source) throws Exception{
		Connection conn = MysqlConnection.getConnection();
		String sql = "SELECT context.AticleId,context.Url FROM context WHERE "
				+ "context.Source = '"+source+"' ORDER BY context.AticleId DESC";
		//通过数据的连接操作数据库
		Statement stmt = conn.createStatement();
		ResultSet result =stmt.executeQuery(sql);
		String url = "";
		if(result.next()){
			url=result.getString("Url");
			return url;
		}
		return "";
	}
}