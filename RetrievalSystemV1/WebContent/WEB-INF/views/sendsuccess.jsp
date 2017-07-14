<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>课题部落--测试版--发送成功</title>
<link rel="stylesheet" type="text/css" href="css/login_1.css"/>
<link rel="stylesheet" type="text/css" href="css/login_2.css"/>
<style>
       body {
           background-color: #eee;
       }
       form {
           max-width: 330px;
           margin: 0 auto;
           padding: 40px 15px;
       }
</style>
</head>
<body>
<!-- 注册成功后的跳转页面 -->
<div class="form-group" style="text-align: center;margin: 250px auto;">
	<h1 style="font-size: 2rem;">课题部落</h1>
	<h2 style="font-size: 1.7rem;">邮件发送成功！</h2>
	<h2 style="font-size: 1.5rem;">请到您的邮箱进行激活...</h2>
	<h2 style="font-size: 1.5rem;">正在返回原页面...</h2>
	<span style="font-size: 1.5rem;" id="totalSecond">3</span>
</div>
<script language="javascript" type="text/javascript"> 
var second = document.getElementById('totalSecond').textContent; 
 
if (navigator.appName.indexOf("Explorer") > -1)  { 
    second = document.getElementById('totalSecond').innerText; 
} else { 
    second = document.getElementById('totalSecond').textContent; 
} 
 
setInterval("redirect()", 1000); 
function redirect() { 
if (second < 0) { 
    location.href = 'personal'; 
} else { 
    if (navigator.appName.indexOf("Explorer") > -1) { 
        document.getElementById('totalSecond').innerText = second--; 
    } else { 
        document.getElementById('totalSecond').textContent = second--; 
    } 
} 
} 
</script>
</body>
</html>