package cn.iflin.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.model.UserBean;
import cn.iflin.server.AdminOperating;
import cn.iflin.server.UserOperating;

@Controller
public class AdminController {
	@RequestMapping("/adminPage")
	public String admin(Model model){
		model.addAttribute("users",AdminOperating.getUsers());
		return "admin";
	}
	@RequestMapping("/massage")
	public String massage(@RequestParam("keyword") String keyword, Model model){
		UserOperating u=new UserOperating();
		UserBean ub = null;
		ArrayList<UserBean> user = new ArrayList<UserBean>();
		ResultSet result=u.selectpersonal(keyword);
		try {
			while(result.next()){
				ub=new UserBean(result.getString("password"),
						result.getString("email"),result.getString("phone"),
						result.getString("realname"),result.getString("workspace"));
				ub.setUserId(result.getString("id"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		user.add(ub);
		model.addAttribute("user", user);
		return "massage";
	}
	@RequestMapping("/changeone")
	public String changeone(){
		return "changeone";
	}
	@RequestMapping("/deleteone")
	public String deleteone(@RequestParam("id") String id){
		UserOperating u=new UserOperating();
		u.executeDELETE(id);
		return "forward:/adminPage";
	}
	@RequestMapping("/adminchange")
	public String personalchange(HttpServletRequest request, @RequestParam("id") String id,@RequestParam("email") String email, @RequestParam("realname") String realname,@RequestParam("Workspace") String workspace){
		UserOperating c=new UserOperating();
		c.UpdateByadmin(id,realname,email,workspace);
		return "forward:/adminPage";
	}
}
