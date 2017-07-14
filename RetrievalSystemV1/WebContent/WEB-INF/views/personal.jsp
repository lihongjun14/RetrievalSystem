<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cn.iflin.server.UserOperating"%>
    <jsp:useBean id="tuijian" class="cn.iflin.server.GetArticles" scope="session"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>课题部落 --测试版--我的个人信息</title>
<link rel="stylesheet" type="text/css" href="css/user_index.css" />
</head>
<body style="width:970px; margin:0 auto;">
	<div class="top">
		<ul>
			<li class="topli">
				<a href="index.html" >主页</a>
			</li>
			<li class="topli">
				<a href="logout">退出登陆</a>
			</li>
			<li class="topli">
				<a href="change">修改信息</a>
			</li>
		</ul>
	</div>  
	<div class="pic">
	<img src="images/logo/logo.png"  width="270" height="129">
	</div>
	<div class="resultArea">
		<div class="result">
			<h2>尊敬的${requestScope.realname}，欢迎回来</h2>
			<p>邮箱地址：${requestScope.email}</p>
			${requestScope.status}
			<p>联系电话：${requestScope.phone}</p>
			<p>工作单位：${requestScope.workspace}</p>
		</div>
		<div class="result" style="margin-top:150px;">
			<h2>为您推荐</h2>
			<c:forEach items="${articles}" var="article">
			<div style="margin-top:8px;">
				<h4><a href="view?articleid=${article.articleId}" target="_blank">${article.articleTtile}</a></h4>
			</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>