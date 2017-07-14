<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="getresult" class="cn.iflin.server.GetArticles" scope="application"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type"?content="text/html;?charset=utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="css/result.css" />
	<title>课题部落 --测试版--最新30条申报信息</title>
</head>
<body style="width:970px; margin:0 auto;">
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
	<div class="idxlogo" >
		<a href="index.html">
		<img src="images/logo/logo.png"  width="270" height="129"></a>
	</div>
	<div class="resultArea">
	<c:forEach items="${results}" var="result">
		<div class="result">
			<div class="first">
				<a class="title" href="view?articleid=${result.articleId}" target="_blank">${result.articleTtile}</a>
			</div>
			<div class="second">
				<a class="source">来源：</a>
				<a class="source" href="http://www.baidu.com target="_blank">${result.articleSource}</a>
				<a class="time">发布时间：${result.articleTime}</a>
			</div>
		</div>
	</c:forEach>
	</div>
</body>
</html>