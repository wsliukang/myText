<%@page import="com.xiteng.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery.min.js"></script>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<script src="css/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top"  style="background:#A0D1F7">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp">夕腾新闻</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
          <!--  <li>
                 <a href="#"><img src="images\图标.png" class="img-thumbnail" width="40" height="40"></a>
     
              </li> -->
<c:if test="${not empty SESSION_USER}">
			 <li>
            <a href="userinfo.jsp">个人信息</a>
              </li>
              <li>
     <c:if test="${SESSION_USER.isAdmin()}">
            <a href="publish.jsp">发布新闻</a>
              </li>
               <li>
            <a href="manage.jsp">管理新闻</a>
              </li>
               <li>
     </c:if>
            <a href="collection.jsp">我的收藏</a>
              </li>
</c:if>

        
      </ul>
      <form class="navbar-form navbar-left" action="SearchNewsServlet">
        <div class="form-group">
          <input type="text" class="form-control" name="key" placeholder="关键字">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
      <ul class="nav navbar-nav navbar-right ">
     
        <!-- <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li> -->
        <%
				User user=(User)session.getAttribute("SESSION_USER");
				if(user!=null){
				
			%>
            
              <li>
                 <a href="userinfo.jsp"><img src="<%=getServletContext().getRealPath("/images/")+user.getHeadShot()%> " class="img-circle" width="40" height="40"></a>
     
              </li>
              <li>
                <a href="LogoutServlet">安全退出</a>
              </li>
            
            <% }else{%>
              <li>
            	<a href="login.jsp" target="_parent">登录</a>
          	  </li>
          	    <li>
            	<a href="register.jsp" target="_parent">注册</a>
            	 </li>
            <%} %>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</body>
</html>