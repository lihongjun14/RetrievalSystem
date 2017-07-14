<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>课题部落--测试版--用户注册</title>
	<script language="JavaScript" type="text/JavaScript" src="js/jquery-1.6.1.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="js/jquery.validate.min.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="js/jquery.metadata.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="js/messages_cn.js"></script>	
    <link rel="stylesheet" type="text/css" href="css/register.css"/>
    <script>
//设定所有输入框规则
$().ready(function() {
    $("#login").validate({
    	rules:{
    		phone:{
    			required:true,
    			minlength:11
    		},
    		password:{
    			required:true,
    			minlength:6
    		},
    		repeatpassword:{
    			equalTo:"#password"
    			
    		}
    	},
    	//设定所有输入框规则提示内容
    	messages:{
    		username:{
    			required:" * 用户名不能为空！",
    			remote: "用户名已存在！"
    		},
    		email:{
    			required:" * 邮箱地址不能为空！"
    		},
    		phone:{
    			required:" * 手机号码不能为空！",
    			minlength: $.format(" * 手机号长度不得小于11位！")
    		},
    		password:{
    			required:" * 用户密码不能为空！",
    			minlength: $.format(" * 密码长度不得小于6位！")
    		},
    		repeatpassword:{
    			equalTo:" * 两次密码输入不一致！"
    		}
    	}
    });
});

</script>
    <style>
        body {
            background-color: #eee;
        }

        form {
            max-width: 330px;
            margin: 0 auto;
            padding: 40px 15px;
        }
        .error{
			color:red;
		}
    </style>
</head>
<body>
<div class="container">
    <form name="regForm" id="login" action="registerAction" method="post">
        <div class="form-group" >
            <h1 style="font-size: 2rem;">科研部落</h1>
			<h2 style="font-size: 1.7rem;">注册</h2>
        </div>
        <div class="form-group">
            <div class="input-group"style="margin: 0 auto;">
                <span class="input-group-addon">邮箱</span>
                <input type="text" id="email" name="email" class="form-control"  placeholder="电子邮箱" />
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon">密码</span>
                <input type="password" class="form-control" name="password" id="password" placeholder="密码" />
            </div>
        </div>
		<div class="form-group">
            <div class="input-group"style="margin: 0 auto;">
                <span class="input-group-addon">确认密码</span>
                <input type="password" id="repeatpassword" name="repeatpassword" class="form-control" placeholder="确认密码" />
            </div>
        </div>
		<div class="form-group">
            <div class="input-group" >
                <span class="input-group-addon">手机</span>
                <input type="text" id="phone"   name="phone" class="form-control"  placeholder="手机" />
            </div>
        </div>
        <div class="form-group">
            <div class="input-group" >
                <span class="input-group-addon">真实姓名</span>
                <input type="text" id="realname"   name="realname" class="form-control"  placeholder="姓名" />
            </div>
        </div>
        <div class="form-group">
            <div class="input-group" >
                <span class="input-group-addon">工作单位</span>
                <input type="text" id="workspace"   name="workspace" class="form-control"  placeholder="工作单位" />
            </div>
        </div>
        <div class="form-group" >
             <input class="btn btn-primary form-control" style="margin: 0 auto;" type="submit" value="注册"/>
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