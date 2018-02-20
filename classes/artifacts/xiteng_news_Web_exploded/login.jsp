<%@page import="com.xiteng.util.CookieEncryptTool"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script src="js/jquery.min.js"></script>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<link rel=stylesheet href="css/login.css">
<script src="css/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
function validate(){
	var username=document.getElementById("username");
	var password=document.getElementById("password");
	var validate=document.getElementById("verifyCode");
	if(password.length<6||password.length>12){
		alert("密码长度不符合要求，请输入6~12位密码");
		password.focus();
		return false;
	}
	return true;
}

function changeValidateCode(){
	document.getElementById("validateCode").src="ValidateCodeServlet?rand="+Math.random();
}


</script>

</head>
<body>
<%
	String username="";
	String password="";
	Cookie cookies[]=request.getCookies();
	if(cookies!=null){
		for(Cookie cookie:cookies){
			if("COOKIE_APPLICANTEMAIL".equals(cookie.getName())){
				username=CookieEncryptTool.decodeBase64(cookie.getValue());
			}
			if("COOKIE_APPLICANTWD".equals(cookie.getName())){
				password=CookieEncryptTool.decodeBase64(cookie.getValue());
			}
		}
	}

%>
<div class="container">
	<form class="form-signin" action="LoginServlet" method="post" onsubmit="return validate();">
		<h2 class="form-signin-heading">请登陆</h2>
		<h5  class="text-warning">${msg_login}<h5>
		<label for="username" class="sr-only">用户名:</label>
		<input type="text" name="username" id="username" class="form-control"
		value="<%=username%>" placeholder="用户名" required autofocus>
		<label for="password" class="sr-only">密码：</label>
		<input type="password" name="password" id="password" class="form-control" 
		value="<%=password%>" placeholder="密码" required>
		<label for="verifyCode" >验证码：</label>
		<input  style="width:70px" type="text" name="verifyCode" id="verifyCode" required>
		<img src="ValidateCodeServlet" id="validateCode" title="单击换一换" onclick="changeValidateCode()">
		<a class="text-danger" href="javascript:changeValidateCode();" > 看不清？</a>
		<button style="width:90px" class="btn  btn-primary" type="submit">登录</button>
		<a href="register.jsp" style="width:90px" class="btn  btn-primary">注册</a>
		<input type="checkbox" checked="checked" id="rememberMe" name="rememberMe" value="true">
		<span class="text-info">记住密码</span>


		
	</form>
</div>
</body>
<script src="js/canvas.js"></script>
</html>