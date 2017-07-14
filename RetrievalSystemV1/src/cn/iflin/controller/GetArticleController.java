package cn.iflin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iflin.server.SearchOperating.SystemGetArticle;


@Controller
//@RequestMapping("/aticle")
public class GetArticleController {
	@RequestMapping(value="/view",method=RequestMethod.GET)
    public String getResult( @RequestParam("articleid") Integer articleId,Model model) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
    	String id = articleId.toString();
    	model.addAttribute("result", SystemGetArticle.getSearch(id));
        return "detail";
    }
}
