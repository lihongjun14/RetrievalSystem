<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>课题部落--测试版--用户信息修改</title>
	<script language="JavaScript" type="text/JavaScript" src="js/jquery-1.6.1.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="js/jquery.validate.min.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="js/jquery.metadata.js"></script>
	<script language="JavaScript" type="text/JavaScript" src="js/messages_cn.js"></script>	
    <link rel="stylesheet" type="text/css" href="css/register.css"/>
    <link rel="stylesheet" type="text/css" href="css/user_index.css" />
    <script>
//设定所有输入框规则
$().ready(function() {
    $("#login").validate({
    	rules:{
    		username:{
    			required:true   			
    		},
    		realname:{
    			required:true

    		},
    		email:{
    			required:true
    		},
    		Workspace:{
    			required:true
    			
    		}
    	},
    	//设定所有输入框规则提示内容
    	messages:{
    		username:{
    			required:" * 用户名不能为空！",
    		},
    		realname:{
    			required:" * 真实姓名不能为空！"
    		},
    		email:{
    			required:" * 邮箱地址不能为空！",
    		},
    		Workspace:{
    			required:" * 工作单位不能为空！",
    		},    		
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
<body style="width:970px; margin:0 auto;">
	<div class="top">
		<ul>
			<li class="topli">
				<a class="topli" href="index.html">主页</a>
			</li>
		</ul>
	</div> 
	<div class="pic" style="margin-top:0 auto;text-align: center;">
		<img src="images/logo/logo.png"  width="270" height="129" >
	</div>

<div class="resultArea" style="margin-top:0 auto;text-align: center;">

	<div id="div" >
		<div style="margin-top:0 auto;text-align: center;">
		<h1>修改个人信息</h1>
		</div>
	</div>

<hr>
<div class="container">
    <form name="regForm" id="login" action="adminchange" method="post">
        <div class="form-group">
                <h3>修改信息</h3>
        </div>
        <input type="text" name="id" id="id" value="${user.getUserId()}" style="visibility:hidden" />    
		<div class="form-group">
            <div class="input-group"style="margin: 0 auto;">
                <span class="input-group-addon">真实姓名</span>
                <input type="text" id="realname" name="realname" class="form-control" value="${user.getRealname()}" placeholder="真实姓名" />
            </div>
        </div>
		<div class="form-group">
            <div class="input-group"style="margin: 0 auto;">
                <span class="input-group-addon">邮箱</span>
                <input type="text" id="email" name="email" class="form-control" value="${user.getEmail()}" placeholder="电子邮箱" />
            </div>
        </div>

		<div class="form-group">
            <div class="input-group" >
                <span class="input-group-addon">工作单位</span>
                <input type="text" id="Workspace"   name="Workspace" class="form-control" value="${user.getWorkspace()}" placeholder="工作单位" />
            </div>
        </div>
        <div class="form-group" >
             <input class="btn btn-primary form-control" style="margin: 0 auto;" type="submit" value="提交"/>
        </div>
    </form>
	</div>
</div>
</body>
</html>