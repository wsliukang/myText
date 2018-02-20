package com.xiteng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiteng.bean.User;
import com.xiteng.dao.UserDao;
import com.xiteng.bean.User;
import com.xiteng.dao.UserDao;
import com.xiteng.util.CookieEncryptTool;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String verifyCode=request.getParameter("verifyCode");
		String rememberMe=request.getParameter("rememberMe");
		PrintWriter out=response.getWriter();
		//��֤��֤��
		String sessionValidateCode=(String) request.getSession().getAttribute("validateCode");
		if(!sessionValidateCode.equals(verifyCode)) {
			request.getSession().setAttribute("msg_login", "��֤���������������");
			response.sendRedirect("login.jsp");
			return;
		}
		
		User user=new User(username, password);
		int userId=user.getId();
		boolean exist=user.isExistUser();
		//applicantIDΪ0���ʾ�˺��������
		if(exist) {
			if(userId!=0) {
				//�û���¼�ɹ������û���Ϣ����Ự����
				request.getSession().setAttribute("SESSION_USER", user);
				//ͨ��cookie��ס���������
				remenberMe(rememberMe,username,password,request,response);
				response.sendRedirect("index.jsp");
			}
			else {
				request.getSession().setAttribute("msg_login", "�����������������");
				response.sendRedirect("login.jsp");
			}
		}else {
			request.getSession().setAttribute("msg_login", "�û���������Ŷ��");
			response.sendRedirect("login.jsp");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void remenberMe(String rememberMe, String email, String password, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		//�ж��Ƿ�ѡ��ס����
		if("true".equals(rememberMe)) {
			Cookie cookie=new Cookie("COOKIE_APPLICANTEMAIL",CookieEncryptTool.encodeBase64(email));
			cookie.setPath("/");
			cookie.setMaxAge(365*24*3600);
			response.addCookie(cookie);
			
			cookie=new Cookie("COOKIE_APPLICANTWD",CookieEncryptTool.encodeBase64(password));
			cookie.setPath("/");
			cookie.setMaxAge(365*24*3600);
			response.addCookie(cookie);
		}else {
			 Cookie cookies[]=request.getCookies();
			 if(cookies!=null) {
				 for (Cookie cookie : cookies) {
					if("COOKIE_APPLICANTEMAIL".equals(cookie.getName())
							||"COOKIE_APPLICANTWD".equals(cookie.getName())) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
			 }
		}
		
	}
}
