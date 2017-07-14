package cn.iflin.controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.iflin.server.GetArticles;
import cn.iflin.server.UserOperating;
import cn.iflin.server.SearchOperating.SystemSearch;
/**
 * 处理用户个人信息修改操作
 */
@Controller
public class PersonalController {
	/**
	 * 个人主页
	 */
	@RequestMapping("/personal")
	public String personal(HttpServletRequest request,ModelMap map,Model model) throws SQLException{	
		UserOperating c=new UserOperating();
		ResultSet result =c.selectpersonal((String)request.getSession().getAttribute("email"));
		String password,email = null,phone,info = "",workspace,realname,status;
		while(result.next()){
			password=result.getString("password");
			email=result.getString("email");
			phone=result.getString("phone");
			workspace = result.getString("workspace");
			realname=result.getString("realname");
			info=result.getString("info");
			status=result.getString("status");
			if(status.equals("0")){
				String statusinfo = "<p><a href=\"sendstatus?email="+email+"\">点击此处激活邮箱</a></p>";
				map.put("status", statusinfo);
			}else{
				map.put("status", "");
			}
			map.put("password", password);
			map.put("email", email);
			map.put("phone", phone);
			map.put("workspace", workspace);
			map.put("realname", realname);
			break;
		}
		if(email.equals("panzejia")){
			return "forward:/adminPage";
		}
		if(info==null){
			GetArticles artile = new GetArticles();
	    	model.addAttribute("articles", artile.getArticle());
		}else if(info.equals("")){
			GetArticles artile = new GetArticles();
	    	model.addAttribute("articles", artile.getArticle());
		}
		else{
			model.addAttribute("articles", GetArticles.getBestArticles(info));
		}
		return "personal";
	}
	/**
	 * 修改用户信息
	 */
	@RequestMapping("/change")
	public String change(){
		return "changeinfo";
	}
	@RequestMapping("/personalchange")
	public String personalchange(HttpServletRequest request, @RequestParam("phone") String phone, @RequestParam("password") String password,@RequestParam("workspace") String workspace){
		UserOperating c=new UserOperating();
		String email=(String) request.getSession().getAttribute("email");
		c.executeUpdate(email, password, phone,workspace);
		return "redirect:/personal";
	}
	
	/**
	 * 用户激活邮箱
	 */
	@RequestMapping(value="/changestatus",method=RequestMethod.GET)
    public String changeStatus( @RequestParam("email") String email,@RequestParam("st") String status,Model model) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
		UserOperating.statusUpdate(email,status);
        return "statussuccess";
    }
}
