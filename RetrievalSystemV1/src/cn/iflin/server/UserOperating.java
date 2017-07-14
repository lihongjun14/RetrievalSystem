package cn.iflin.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import cn.iflin.model.MysqlConnection;
/**
 * 实现对用户的操作
 * @author Jaypan
 *
 */
public class UserOperating {
	private static Statement st;
	private static Connection conn=null;
//	连接到数据库
    public UserOperating() {
        try
        {
        	conn = MysqlConnection.getConnection();
            st = conn.createStatement();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
  /**
   * 查询数据表的所有记录
   * @return
   */
    public ResultSet select(){ 
        ResultSet result = null;
        try
        {
            result = st.executeQuery("SELECT * FROM userinformation");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 查询指定用户名的记录
     * @param name
     * @return
     */
    public ResultSet selectpersonal(String email){ 
        ResultSet result = null;
        try
        {
            result = st.executeQuery("SELECT * FROM userinformation Where email='"+email+"'");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    //输出结果
    public void printTABLE(ResultSet result) {
        try
        {
            while(result.next()) {
            }
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * /*更新指定用户名的记录
     * @param password
     * @param email
     * @param phone
     * @param workspace
     */
    public void executeUpdate(String password,String email,String phone,String workspace){
    	try{
            String sql = "UPDATE userinformation SET password = '"+password+"', phone ='"+phone+"',workspace='"+workspace+"' WHERE email ='"+email+"'";
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
    public void UpdateByadmin(String id,String realname,String email,String workspace){
    	try{
            String sql = "UPDATE userinformation SET  realname ='"+realname+"',email='"+email+"',workspace='"+workspace+"' WHERE id ='"+id+"'";
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
    /**
     * 增加新的用户记录到数据表
     * @param email
     * @param password
     * @param phone
     * @param realname
     * @param workspace
     */
    public void executeADD(String email,String password,String phone,String realname,String workspace){
    	try{
            String sql = "INSERT userinformation VALUE(NULL,'"+password+"','"+email+"','"+phone+"','"+realname+"','"+workspace+"',NULL,0,NULL)";
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
    /**
     *     删除指定id的记录
     * @param id
     */
    public void executeDELETE(String id){
    	try{
            String sql = "DELETE FROM userinformation WHERE id = "+id;  
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
    /**
     * 更改用户激活状态
     */
    public static void statusUpdate(String email,String status){
    	try{
            String sql = "UPDATE userinformation SET status='"+status+"' WHERE email ='"+email+"'";
            st.executeUpdate(sql);
            }catch(SQLException e1) {    
                // TODO Auto-generated catch block    
                e1.printStackTrace();   
            } 
    }
    /**
     * 将真实姓名和工作单位传入python文件中进行爬取其相关内容
     * @param realname
     * @param workspace
     */
    public static void addInfo(String realname,String workspace){
    	System.out.println("开始");
		try {
            //需传入的参数
            //设置命令行传入参数
            String[] args1 = new String[] { "python", "C:\\addinfo.py", realname, workspace};
            Process pr = Runtime.getRuntime().exec(args1);
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
