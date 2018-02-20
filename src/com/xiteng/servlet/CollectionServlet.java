package com.xiteng.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiteng.bean.Collection;
import com.xiteng.bean.User;

/**
 * Servlet implementation class CollectionServlet
 */
@WebServlet("/CollectionServlet")
public class CollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		User user=(User)request.getSession().getAttribute("SESSION_USER");
		if("delete".equals(type)) {
			int newsId=Integer.parseInt(request.getParameter("newsId"));
			int userId=user.getId();
			Collection collection=new Collection(userId, newsId);
			collection.deleteCollection();
		}else if("add".equals(type)) {
			int newsId=Integer.parseInt(request.getParameter("newsId"));
			int userId=user.getId();
			Collection collection=new Collection(userId, newsId);
			collection.addCollection();
		}
		user.updateCollections();
		request.getSession().setAttribute("SESSION_USER", user);
		if("list".equals(request.getParameter("page")))
			response.sendRedirect("collection.jsp");
		else
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
