<%@page import="java.util.ArrayList"%>
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
<title>首页</title>
<script src="js/jquery.min.js"></script>
<link rel=stylesheet href="css/bootstrap/css/bootstrap.css">
<script src="css/bootstrap/js/bootstrap.js"></script>
<link rel=stylesheet href="css/index.css">
</head>
<body>

<jsp:include page="top.jsp"></jsp:include>

<section class="content-wrap">
        <div class="container">
            <div class="row">
                <main class="col-md-8 main-content">
                <%
	NewsDao dao=new NewsDao();
	List<News>list=dao.getAllNews();
	if(list!=null){
		for(News news:list){
		%>
                <div class="panel panel-primary">
                <div class="panel-heading">&nbsp;</div>
                    <article class="post tag-abuot-ghost tag-ghost-in-depth tag-zhu-shuo">
                        <div class="post-head">
                            <h1 class="post-title">
                                <a href="NewsManageServlet?type=read&id=<%=news.getId() %>"><%=news.getTitle() %></a>
                            </h1>
                            <div class="post-meta">
                                <span class="author">
                                    作者
                                    <a href="#"><%=news.getAuthor() %></a>
                                    </span>
                                    •
                                    <time class="date" datetime="2015-10-10"><%=news.getPublishDate() %></time>
                            </div>
                        </div>
                        <div class="post-content">
                            <p>
                             <%=news.getBrief() %>
                            </p>
                        </div>
                        <div class="post-permalink">
                            <a href="NewsManageServlet?type=read&id=<%=news.getId() %>" class="btn btn-default">阅读全文</a>
                        </div>
                        <hr>
                    </article>
                   </div>
                                		<% 
		}
	}
%>
                </main>
   
<aside class="col-md-4 sidebar">


<div class="panel panel-info">
  <div class="panel-heading">联系我们</div>
  <div class="panel-body">
   QQ群：390050833<br>
        邮箱：1179168898@qq.com
  </div>
</div>
<!-- start tag cloud widget -->
<div class="panel panel-warning">
  <div class="panel-heading">最近浏览</div>
  <div class="panel-body">
  <div class="content tag-cloud">
<% 
				String history=(String)session.getAttribute("HistoryList");
                ArrayList<News> newsList = dao.getHistoryList(history);
                if(newsList!=null&&newsList.size()>0 )
                {
                   for(News news:newsList)
                   {
             %>
              <a href="NewsManageServlet?type=read&id=<%=news.getId() %>"><%=news.getTitle() %></a><br>
             
             
             <%
                   }
                }
          %>
	</div>
  </div>
</div>

    	      </aside>
          </div>
        </div>
    </section>

	

</body>
</html>