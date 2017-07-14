<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="getarticle" class="cn.iflin.server.SearchOperating" scope="application"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/result.css" />
<title>${result.articleTtile}--课题部落</title>
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
	<div class="resultArea" style=" text-align:center;">
		<div style="color:#000000;font-size:25px;" >
			${result.articleTtile}
		</div>
		<div style="color:#505050;font-size:20px;" >
			发布时间：${result.articleTime}
		</div>
	
		<div class="article" style="font-family: 微软雅黑;font-size: 16px; text-align:left;">
			${result.articleText}
		</div>
	</div>
	
</body>
</html>