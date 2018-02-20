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
		<form class="form-signin" action="UpdateUserInfoServlet" method="post" >
		<label for="nickname" >昵称:</label>
		<input type="text" name="nickname" id="nickname" class="form-control"
		 value="${SESSION_USER.getUserInfo().getNickName()}" required autofocus>
		 <br>
		 <label for="sex">性别:</label>
         <label class="radio-inline" >
         <c:choose>
         <c:when test="${SESSION_USER.getUserInfo().isMan()}">
          <input type="radio"  name="sex" value="男" checked>男</label>
           <label class="radio-inline" >
            <input type="radio"  name="sex" value="女">女
          </c:when>
          <c:otherwise>
          <input type="radio"  name="sex" value="男" >男</label>
           <label class="radio-inline" >
            <input type="radio"  name="sex" value="女" checked>女
          </c:otherwise>
         </c:choose>
     
         

             </label><br>
		
		<label for="age" >年龄：</label>
		<input type="text" name="age" id="age" class="form-control" 
		 value="${SESSION_USER.getUserInfo().getAge()}" required>
		<label for="address" >地址：</label>
		<input type="text" name="address" id="address" class="form-control" 
		 value="${SESSION_USER.getUserInfo().getAddress()}" required>
		 <label for="telephone" >电话号码：</label>
		<input type="text" name="telephone" id="telephone" class="form-control" 
		 value="${SESSION_USER.getUserInfo().getTelephone()}" required>
		 <br>
		<button class="btn btn-info">保存修改</button>
		<a href="index.jsp"   class="btn btn-info">回到首页</a>
	</form>
<%-- 	<form class="form-horizontal">
  <div class="form-group">
    <label class="col-sm-2 control-label">${SESSION_USER.getHeadShot()}</label>
    <div class="col-sm-10">
      <p class="form-control-static">email@example.com</p>
    </div>
  </div>
  </form> --%>
  
	</div>
	</div>
	</div>
	</div>
<!-- <a href="updateUserInfo.jsp">修改信息</a> -->
<script type="text/javascript">
var test1=new Vcity.CitySelector({input:'address'});
</script>
</body>
</html>