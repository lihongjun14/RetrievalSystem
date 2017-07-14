package cn.iflin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * 数据库连接
 */
public class MysqlConnection {
	private static final String URL="jdbc:mysql://127.0.0.1:3306/retrievalsystemdb?autoReconnect=true";
	private static final String USER="root";
	private static final String PASSWORD="0503";
	private static Connection conn=null;
	//使用静态方法，优先运行静态方法
	static {
		try {
			//加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//连接本地数据库
			conn =DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//获取连接并返回
	public static Connection getConnection (){
		return conn;
	}
}


