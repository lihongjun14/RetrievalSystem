package cn.iflin.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.server.GetArticles;
import cn.iflin.server.SearchOperating;
import cn.iflin.server.SearchOperating.SystemSearch;
/**
 * 用户管理
 * 
 * @author zjn
 */
@Controller
public class IndexController {
    @RequestMapping("index.html")
    public String index1(Model model) {
    	GetArticles artile = new GetArticles();
    	model.addAttribute("articles", artile.getArticle());
        return "forward:index1.jsp";
    }
    @RequestMapping("")
    public String index2(Model model) {
    	GetArticles artile = new GetArticles();
    	model.addAttribute("articles", artile.getArticle());
        return "forward:index1.jsp";
    }
    
    @RequestMapping(value="/getresult",method=RequestMethod.GET)
    public String getResult( @RequestParam("sw") String keyword,Model model) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
    	SystemSearch getResult =new SystemSearch();
    	model.addAttribute("results", getResult.getSearch(keyword));
        return "result";
    }
    @RequestMapping(value="/articles.html")
    public String getArticles( Model model) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
    	model.addAttribute("results", GetArticles.getArticles());
        return "articles";
    }
}