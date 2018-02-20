package com.xiteng.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiteng.bean.News;

/**
 * Servlet implementation class PublishServlet
 */
@WebServlet("/NewsManageServlet")
public class NewsManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		News news=null;
		if("add".equals(type)) {
			String title=request.getParameter("title");
			String brief=request.getParameter("brief");
			String author=request.getParameter("author");
			String content=format(request.getParameter("content"));
			Date publishDate=new Date();
			news=new News(title, brief, content, author, publishDate);
			news.addNews();
			response.sendRedirect("index.jsp");
		}else if("read".equals(type)) {
			int id=Integer.valueOf(request.getParameter("id"));
			news=new News(id);
			request.getSession().setAttribute("CURRENT_READ", news);
			addIntoHistory(request,response);
			response.sendRedirect("content.jsp");
		}else if("edit_start".equals(type)) {
			int id=Integer.valueOf(request.getParameter("id"));
			news=new News(id);
			request.getSession().setAttribute("CURRENT_READ", news);
			response.sendRedirect("update.jsp");
		}else if("edit_end".equals(type)) {
			int id=Integer.valueOf(request.getParameter("id"));
			String title=request.getParameter("title");
			String brief=request.getParameter("brief");
			String author=request.getParameter("author");
			String content=format(request.getParameter("content"));
			Date publishDate=new Date();
			news=new News(id);
			news.updateNews(title, brief, content, author, publishDate);
			response.sendRedirect("manage.jsp");
		}else if("delete".equals(type)) {
			int id=Integer.valueOf(request.getParameter("id"));
			news=new News(id);
			news.deleteNews();
			response.sendRedirect("manage.jsp");
		}
	}

	private void addIntoHistory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String list ="";
        //从客户端获得Cookies集合
        Cookie[] cookies = request.getCookies();
        //遍历这个Cookies集合
        if(cookies!=null&&cookies.length>0)
        {
            for(Cookie c:cookies)
            {
                if(c.getName().equals("History_Cookie"))
                {
                   list = c.getValue();
                   break;
                }
            }
        }
        
        list+=request.getParameter("id")+",";
        //如果浏览记录超过1000条，清零.
        String[] arr = list.split(",");
        if(arr!=null&&arr.length>0)
        {
            if(arr.length>=1000)
            {
                list="";
            }
        }
        Cookie cookie = new Cookie("History_Cookie",list);
        response.addCookie(cookie);
        request.getSession().setAttribute("HistoryList", list);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public String format(String content) {
		return  content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>"); 
	}
}
