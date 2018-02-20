package com.xiteng.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiteng.bean.News;
import com.xiteng.dao.NewsDao;

/**
 * Servlet implementation class SearchNewsServlet
 */
@WebServlet("/SearchNewsServlet")
public class SearchNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String key=request.getParameter("key");
		NewsDao dao=new NewsDao();
		List<News>list1=dao.getAllNews();
		List<News>list2=new ArrayList<News>();
		if(list1!=null){
			for(News news:list1){
				if(news.getTitle().contains(key)
				||news.getBrief().contains(key)
				||news.getAuthor().contains(key)) {
					list2.add(news);
				}
			}
		}
		request.getSession().setAttribute("SEARCH_RESULT", list2);
		response.sendRedirect("search.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
