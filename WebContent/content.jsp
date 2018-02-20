<%@page import="com.xiteng.bean.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.xiteng.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery.min.js"></script>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<script src="css/bootstrap/js/bootstrap.js"></script>
<link rel=stylesheet href="css/index.css">
<title>新闻内容</title>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include><br>
   <article class="post tag-abuot-ghost tag-ghost-in-depth tag-zhu-shuo">
                        <div class="post-head">
                            <h1 class="post-title">
                                <a href="content.jsp">${CURRENT_READ.getTitle()}</a>
                            </h1>
                            <div class="post-meta">
                                <span class="author">
                                    作者
                                    <a href="#">${CURRENT_READ.getAuthor()}</a>
                                    </span>
                                    •
                                    <time class="date" datetime="2015-10-10">${CURRENT_READ.getPublishDate()}</time>
                            </div>
                        </div>
                        <div class="post-content">
                            <p>
                          ${CURRENT_READ.getContent()}
                            </p>
                        </div>
<c:if test="${not empty SESSION_USER}">
<div class="container">
       <div class="row">
       		<div class="col-md-2 col-md-offset-10">
<c:choose>
<c:when test="${SESSION_USER.isBeCollected(CURRENT_READ.getId())}">
<a href="CollectionServlet?type=delete&newsId=${CURRENT_READ.getId()}">
<span class="glyphicon glyphicon-star" aria-hidden="true">取消收藏</span>
</a>
</c:when>
<c:otherwise>
<a href="CollectionServlet?type=add&newsId=${CURRENT_READ.getId()}">
<span class="glyphicon glyphicon-star-empty" aria-hidden="true">点击收藏</span>
</a>
</c:otherwise>
</c:choose>
</div>
</div>
</div>
</c:if>
                        <hr>
                    </article>
    <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">   
<div class="panel panel-info">
  <div class="panel-heading">新闻评论</div>
  <div class="panel-body">
<c:forEach items="${CURRENT_READ.getComments()}" var="comment" >
${comment.getNickname()}:
<div class="col-md-2 col-md-offset-10">  
<a href="CommentServlet?type=favour&id=${comment.getId()}&favour=${comment.getFavour()+1}">
<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"><span class="badge" style="background:#0B7EE2">${comment.getFavour()}</span></span>
</a>
<a href="CommentServlet?type=disfavour&id=${comment.getId()}&disfavour=${comment.getDisfavour()+1}">
<span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"><span class="badge" style="background:#E22B0B">${comment.getDisfavour()}</span></span>
</a>
</div>
<br>
${comment.getContent()}<br>
${comment.getCommentTime()}
<hr>
</c:forEach> 
 
<hr>
<c:choose>
<c:when test="${not empty SESSION_USER}">
 <form action="CommentServlet?type=add" method="post" onsubmit="return check();">
 							<div class="col-md-8 col-md-offset-2">
               <!--form-group对每个表单域进行分组-->
                  <div class="form-group">
                      <label>评论:</label>
                      <!--给表单域添加form-control样式，能够美化文本框-->
                    <textarea class="form-control" rows="4" name="content" ></textarea>
                  </div>
                  <div  class="col-md-offset-2">
               	<button style="width:90px" class="btn  btn-warning" type="submit">发表</button>
               	&nbsp; 	&nbsp; 	&nbsp; 	&nbsp; 	&nbsp; 	&nbsp;
                <a href="index.jsp" style="width:90px" class="btn  btn-info">首页</a>
               	</form>
            </c:when>
<c:otherwise>
<h5>使用评论功能请先<a href="login.jsp">登录</a></h5>
<a href="index.jsp" style="width:90px" class="btn  btn-info">首页</a>
</c:otherwise>
</c:choose>
               	</div>
               	</div>
            </div>
         </div>
       </div>
  </body>
</html>