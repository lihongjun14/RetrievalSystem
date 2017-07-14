<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cn.iflin.server.AdminOperating"%>
     <%@ page import="cn.iflin.server.UserOperating"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"><title>课题部落 -- 测试版--后台管理</title>
<link rel="stylesheet" type="text/css" href="css/user_index.css" />

</head>
<body style="width:970px; margin:0 auto;">
	<div class="top">
		<ul>
			<li class="topli">
				<a href="index.html" >主页</a>
			</li>
		</ul>
	</div>  
	<div class="pic">
	<img src="images/logo/logo.png"  width="270" height="129">
	</div>
	<div class="resultArea">
		<div class="result">
			<h2>尊敬的${requestScope.realname}，欢迎回来</h2>
		</div>
		<div class="result" style="margin-top:150px;">
			<h2>用户管理</h2>
			<c:forEach items="${users}" var="user">
			<div>
				<a href="massage?keyword=${user.getEmail()}">${user.getRealname()}${user.getWorkspace()}</a>				
			</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>