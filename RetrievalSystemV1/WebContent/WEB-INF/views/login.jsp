<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>课题部落--测试版--用户登陆</title>
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
<div class="container">
    <form name="regForm" action="sign" method="post">
        <div class="form-group" style="text-align: center;">
            <h1 style="font-size: 2rem;">课题部落</h1>
			<h2 style="font-size: 1.7rem;">登录</h2>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">邮箱</span>
                <input autocomplete="off" type="text" class="form-control" name="email" id="email" placeholder="邮箱" />
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">密码</span>
                <input  type="password" class="form-control" name="password" id="password" placeholder="密码" />
            </div>
        </div>
        <div class="form-group">
             <button class="btn btn-primary form-control" type="submit">登录</button>
             <a style="float:left" href="index.html">返回主页</a>
             <a style="float:right" href="register">还没有账号？</a>
        </div>
        
    </form>
</div>
<script type="application/javascript">
    setTimeout(function() {
        document.getElementById("email").focus();
    }, 10);
</script>
<!--[if IE]>
<style>
    .container {
        display: none;
    }
    .not-support {
        padding: 30px;
        margin: 30px;
    }
</style>
<div class="not-support">
    系统不支持此版本的浏览器，Windows XP或Vista用户请下载
    <a href="http://dl.google.com/release2/h8vnfiy7pvn3lxy9ehfsaxlrnnukgff8jnodrp0y21vrlem4x71lor5zzkliyh8fv3sryayu5uk5zi20ep7dwfnwr143dzxqijv/49.0.2623.112_chrome_installer.exe">Google Chrome v49</a>，
    Windows 7及以上用户请下载
    <a href="https://dl.google.com/release2/11sx7qq3lmncwfwkxnj8si8rq6me2v498iogaovom8062r5g3bwn5s6l64nt9yzjvua2kxq5sdnwbgsab2b569l5ey529uyw5nkv/51.0.2704.84_chrome_installer.exe">Google Chrome v51 32位</a>或
    <a href="https://dl.google.com/release2/rkoforcsrkbke4r1b5s6gdj2a2aonn3m3l2d2l1j4pwgv2lcpjro3vz0ymb6snqur8lfocj6f8erh4ad91qndxeegy5chbkt167/51.0.2704.84_chrome_installer_win64.exe">Google Chrome v51 64位</a>。
</div>
<![endif]-->
</body>
</html>