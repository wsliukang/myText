package com.xiteng.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.xiteng.bean.User;
import com.xiteng.dao.UserDao;

/**
 * Servlet implementation class UpdateHeadShotServlet
 */
@WebServlet("/UpdateHeadShotServlet")
@javax.servlet.annotation.MultipartConfig
public class UpdateHeadShotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateHeadShotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取上传的文件域
				Part part=request.getPart("headShot");
				//获取上传的文件名称
				String fileName=part.getSubmittedFileName();
				//为防止上传文件重名，对文件重命名
				System.out.println("文件名:"+fileName);
				String newFileName=System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
				//将上传的文件保存在服务器项目发布的/images目录下
				String filepath=getServletContext().getRealPath("/images");
				//String filepath2=getServletContext().getContextPath();
				System.out.println("头像保存路径为:"+filepath);
				File f=new File(filepath);
				if(!f.exists()) 
						f.mkdirs();
				part.write(filepath+"/"+newFileName);
				//从会话中获得当前用户简历标识
				User user=(User)request.getSession().getAttribute("SESSION_USER");
				user.setHeadShot(newFileName);
				request.getSession().setAttribute("SESSION_USER", user);
				//照片更新成功，返回主页
				response.sendRedirect("userinfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
