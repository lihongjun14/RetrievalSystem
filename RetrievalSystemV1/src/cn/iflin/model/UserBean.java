package cn.iflin.model;
/**
 * 
 *处理用户信息的bean
 *
 */
public class UserBean {
	private String userid;
	private String password;
	private String email;
	private String phone;
	private String realname;
	private String workspace;
	public UserBean(String password,String email,String phone,String realname,String workspace){
		this.password=password;
		this.email=email;
		this.phone=phone;
		this.realname=realname;
		this.workspace=workspace;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getRealname() {
		return realname;
	}
	public String getWorkspace() {
		return workspace;
	}
	public void setUserId(String userid){
		this.userid=userid;
	}
	public String getUserId(){
		return userid;
	}
}
