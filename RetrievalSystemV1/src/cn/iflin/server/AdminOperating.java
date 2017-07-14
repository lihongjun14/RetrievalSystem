package cn.iflin.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.iflin.model.MysqlConnection;
import cn.iflin.model.UserBean;
/**
 * 后台管理
 * @author Jaypan
 *
 */
public class AdminOperating {
	public static  ArrayList<UserBean> getUsers(){
		Connection conn = MysqlConnection.getConnection();
		//通过数据的连接操作数据库
		Statement stmt;
		ArrayList<UserBean> users = new ArrayList<UserBean>();
		try {
			stmt = conn.createStatement();
			ResultSet result =stmt.executeQuery("SELECT * FROM userinformation");
			UserBean ub = null;
			while(result.next()){
				ub = new UserBean(result.getString("password"),
						result.getString("email"),result.getString("phone"),
						result.getString("realname"),result.getString("workspace"));
				ub.setUserId(result.getString("id"));
				users.add(ub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		};
		return users;
	}
}
