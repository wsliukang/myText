package com.xiteng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiteng.bean.User;
import com.xiteng.dao.UserDao;
import com.xiteng.dao.UserDao;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String verifyCode=request.getParameter("verifyCode");
		String sessionValidateCode=(String) request.getSession().getAttribute("validateCode");
		if(!sessionValidateCode.equals(verifyCode)) {
			request.getSession().setAttribute("msg_register", "��֤���������������");
			response.sendRedirect("register.jsp");
			return;
		}
		User user=new User(username,password);
		boolean flag=user.isExistUser();
		System.out.println(flag);
		if(flag) {
			request.getSession().setAttribute("msg_register", "�û����ѱ�ע����ˣ���һ����");
			response.sendRedirect("register.jsp");
		}
		else {
			user.save();
			request.getSession().setAttribute("msg_login", "��ϲ��ע��ɹ�,��Ϊ��"+user.getId()+"���û�,���½���԰�");
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

}
