<%@page import="com.xiteng.bean.News"%>
<%@page import="java.util.List"%>
<%@page import="com.xiteng.dao.NewsDao"%>
<%@page import="com.xiteng.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>新闻管理</title>
<script src="js/jquery.min.js"></script>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<script src="css/bootstrap/js/bootstrap.js"></script>
<link rel=stylesheet href="css/index.css">
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
 <div  class="row">
       <div class="col-md-10 col-md-offset-1">
<div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading">新闻列表</div>

  	<table class="table table-bordered table-hover table-striped">
  	<thead>
     <th>标题</th>
     <th>简介</th>
     <th>作者</th>
     <th>发布时间</th>
     <th>操作</th>
  </thead>
    <tbody>
             <%
	NewsDao dao=new NewsDao();
	List<News>list=dao.getAllNews();
	if(list!=null){
		for(News news:list){%>
     	
     <tr>
        <td><a href="NewsManageServlet?type=read&id=<%=news.getId() %>"><%=news.getTitle() %></a></td>  
        <td><%=news.getBrief() %></td>  
        <td><%=news.getAuthor() %></td>  
      	<td><%=news.getPublishDate() %></td>
      	<td>
          <a href="NewsManageServlet?type=edit_start&id=<%=news.getId() %>" class="btn btn-info btn-xs">编辑</a>
           <a href="NewsManageServlet?type=delete&id=<%=news.getId() %>" class="btn btn-danger btn-xs">删除</a>
        </td>

     </tr>
 		
<% 
	}
}
%>
 </tbody>
</table>
</div>
</div>
</div>
</body>
</html>