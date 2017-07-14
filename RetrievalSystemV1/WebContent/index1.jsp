<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="getarticles" class="cn.iflin.server.GetArticles" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/index.css" />
<title>课题部落 --测试版</title>
</head>
<body style="width:970px; margin:0 auto;">
<div >
	<div class="top">
		<ul>
			<li class="topli">
			<%	
			if(session.getAttribute("realname")==null){
				out.print("<a class=\"topli\" href=\"login\">登陆</a>");
			}else{
				out.print("<a class=\"topli\" href=\"personal\">"+session.getAttribute("realname")+"</a>");
			} %>
			</li>
		</ul>
	</div>

<div class="body">
    <div class="pic" style="margin-top: 40px;">
		<a href="index.html">
		<img src="images/logo/logo.png" width="270" height="129"></a>
    </div>
    <div class="search">
        <form action="getresult" method="get"  >
            <input autocomplete="off" class="searchinput" id="search" type="text" name="sw" value="">
			<input class="inputbutton" id="submit" type="submit" value="搜索" />
        </form>
        <div style="margin-top: 30px">
        	</div><a href="articles.html" >最新30条申报信息</a>
        </div>
    </div>
</div>
<div class="foot" style="width:970px;height:300px;margin-top: 150px">
    <div class="container"style="width:970px;">
	<div class="row">
	<c:forEach items="${articles}" var="article">
  		<div class="col-md-4">
    		<h3><a href="view?articleid=${article.articleId}" target="_blank">${article.articleTtile}</a></h3>
    		<p>${article.articleText}</p>
  		</div>
  	</c:forEach>
	</div>
   </div>
</div>
<div style="margin:0px auto;text-align: center;">
	<p>Copyright©2017 - 检索小队 版权所有</p>
</div>
</body>
</html>