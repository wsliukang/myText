<%@page import="com.xiteng.dao.UserDao"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的收藏</title>
<script src="js/jquery.min.js"></script>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<script src="css/bootstrap/js/bootstrap.js"></script>
<link rel=stylesheet href="css/index.css">
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<c:choose>
<c:when test="${not empty SESSION_USER.getCollections()}">
<div  class="row">
       <div class="col-md-10 col-md-offset-1">
<div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading">收藏列表</div>

  	<table class="table table-bordered table-hover table-striped">
  	<thead>
     <th>标题</th>
     <th>简介</th>
     <th>操作</th>
  </thead>
    <tbody>
<c:forEach items="${SESSION_USER.getCollections()}" var="collection" >
 <div  class="row">
       <div class="col-md-10 col-md-offset-1">

<tr>
        <td><a href="NewsManageServlet?type=read&id=${collection.getNewsId()}">${collection.getTitle()}</a></td>  
        <td>${collection.getBrief()}</td>  
      	<td>
           <a href="CollectionServlet?type=delete&newsId=${collection.getNewsId()}&page=list" class="btn btn-danger btn-xs">移除</a>
        </td>

     </tr>
     
</c:forEach>
</tbody>
</table>
</div>
</div>
</div>
</c:when>
<c:otherwise>
<h3>你的收藏还没有任何新闻哦，快去把喜欢的新闻加入收藏吧~</h3>
</c:otherwise>
</c:choose>
</div>
</div>
</body>
</html>