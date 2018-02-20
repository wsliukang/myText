package com.xiteng.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiteng.bean.Comment;
import com.xiteng.bean.News;
import com.xiteng.bean.User;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		int newsId=((News)request.getSession().getAttribute("CURRENT_READ")).getId();
		if("add".equals(type)) {
			String content=request.getParameter("content");
			User user=(User)request.getSession().getAttribute("SESSION_USER");
			int userId=user.getId();
			String nickname;
			if(user.getUserInfo()!=null&&user.getUserInfo().getNickName()!=null&&!(("").equals(user.getUserInfo().getNickName())))
				nickname=user.getUserInfo().getNickName();
			else
				nickname=user.getUsername();
			Date commentTime=new Date();
			Comment comment=new Comment(newsId, userId, nickname, content, 0, 0, commentTime);
			comment.addComment();
		}
		else if("favour".equals(type)) {
			int id=Integer.parseInt(request.getParameter("id"));
			int favour=Integer.parseInt(request.getParameter("favour"));
			Comment comment=new Comment(id);
			comment.updateFavour(favour);
		}else if("disfavour".equals(type)) {
			int id=Integer.parseInt(request.getParameter("id"));
			int disfavour=Integer.parseInt(request.getParameter("disfavour"));
			Comment comment=new Comment(id);
			comment.updateDisfavour(disfavour);
		}
		News news=new News(newsId);
		request.getSession().setAttribute("CURRENT_READ", news);
		response.sendRedirect("content.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
