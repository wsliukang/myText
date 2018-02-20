<%@page import="com.xiteng.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<script src="js/jquery.min.js"></script>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<link rel=stylesheet href="css/login.css">
<link rel="stylesheet" href="css/cityselect.css">
<link rel=stylesheet href="css/index.css">
<script type="text/javascript" src="js/cityselect.js"></script>
<script src="css/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	function validate() {
		var headShot = document.getElementById("headShot");
		if (headShot.value == "") {
			alert("请选择要上传的头像！");
			headShot.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<%-- <style>
body { padding-top: 100px; }
</style>
<jsp:include page="top.jsp"></jsp:include> --%>
<%
   User user=(User)session.getAttribute("SESSION_USER");
%>
<!--使用row建立bootstrap的栅格系统 ，bootstrap将屏幕划分为12列 然后使用login-box设定这个div为登录窗口样式-->
  <div class="row login-box">
  <!--定义窗口横跨4列 col-md-offset-4使得窗口右移4列，形成居中显示效果-->
     <div  class="col-md-4 col-md-offset-4">
     <!--使用panel定义面板 panel-primary规定面板的皮肤颜色(也可以设为panel-info等)-->
         <div  class="panel panel-info">
            <!--设定头部标题栏-->
            <div class="panel-heading">用户信息</div>
            <!--设定面板主体，我们的表单元素要全部放在这里面-->
            <div class="panel-body">
 		<form name="formen" class="form-signin" action="UpdateHeadShotServlet" method="post"  
  		 enctype="multipart/form-data"	onsubmit="return validate();"	>
		  	<img  class="img-thumbnail" src="<%=getServletContext().getRealPath("/images/")+user.getHeadShot()%>"align="top">
		  	<input class="media-object" name="headShot" id="headShot" type="file" style="display:none" onChange="document.formen.path.value=this.value">
		  <div row>
		  <div class="col-md-12">
   		 <div class="input-group">
      	<input type="text" class="form-control" name="path" style="padding-top: 3px;" readonly>
     		 <span class="input-group-btn">
       			 <button class="btn btn-default" type="button" onclick="document.formen.headShot.click()">选择头像</button>
     		 </span>
    		</div>
 		 </div>
 		 </div>
 		 <div row>
		  <div class="col-md-6 col-md-offset-3">
		  	<input class="btn btn-info" name="submit" id="submit" type="submit" value="点击上传头像">
		  	</div>
		  	</div>
		</form>
		<br><br>
		<c:choose>
		<c:when test="${not empty SESSION_USER.getUserInfo()}">
		<br>
		<h4><%=getServletContext().getRealPath("/images/")+user.getHeadShot()%></h4>
		<h4>昵称:${SESSION_USER.getUserInfo().getNickName()}</h4>
		<h4>性别:${SESSION_USER.getUserInfo().getSex()}</h4>
		<h4>年龄：${SESSION_USER.getUserInfo().getAge()}</h4>
		<h4>地址：${SESSION_USER.getUserInfo().getAddress()}</h4>
		 <h4>电话号码：${SESSION_USER.getUserInfo().getTelephone()}</h4> 
		<a href="updateUserInfo.jsp"   class="btn btn-info">修改信息</a>
		<a href="index.jsp"   class="btn btn-info">回到首页</a>
		</c:when>
		<c:otherwise>
		<br>
		<div row>
		  <div class="col-md-6 col-md-offset-3">
		<a href="updateUserInfo.jsp" >完善个人信息</a>
		<br>&nbsp;&nbsp;&nbsp;
		<a href="index.jsp"   class="btn btn-info">回到首页</a>
		</div>
		</div>
		
		</c:otherwise>
		
		</c:choose>
	</form>
  
	</div>
	</div>
	</div>
	</div>
</body>
</html>