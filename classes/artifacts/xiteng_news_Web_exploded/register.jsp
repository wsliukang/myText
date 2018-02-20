<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册 </title>
<script src="js/jquery.min.js"></script>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<script src="css/bootstrap/js/bootstrap.js"></script>
<link rel=stylesheet href="css/login.css">
<script type="text/javascript">
function validate(){
	var username=document.getElementById("username");
	var password=document.getElementById("password");
	if(username.value==""){
		alert("用户名不能为空");
		username.focus();
		return false;
	}
	if(password.value==""){
		alert("密码不能为空");
		password.focus();
		return false;
	}else if(password.length<6||password.length>12){
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
<jsp:include page="top.jsp"></jsp:include>
<div class="container">
	<form class="form-signin" action="RegisterServlet" method="post" onsubmit="return validate();">
		<h2 class="form-signin-heading">请注册:</h2>
		<h4  class="text-danger">${msg_register}<h4>
		<label for="username" class="sr-only">用户名:</label>
		<input type="text" name="username" id="username" class="form-control" placeholder="用户名" required autofocus>
		<label for="password" class="sr-only">密码：</label>
		<input type="password" name="password" id="password" class="form-control" placeholder="密码" required>
		<label for="verifyCode" >验证码：</label>
		<input  style="width:70px" type="text" name="verifyCode" id="verifyCode" required>
		<img src="ValidateCodeServlet" id="validateCode" title="单击换一换" onclick="changeValidateCode()">
		<a class="text-danger" href="javascript:changeValidateCode();" > 看不清？</a>
		<button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
	</form>
</div>
</body>
<script src="js/canvas.js"></script>
</html>