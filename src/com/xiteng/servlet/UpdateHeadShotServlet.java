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
		//��ȡ�ϴ����ļ���
				Part part=request.getPart("headShot");
				//��ȡ�ϴ����ļ�����
				String fileName=part.getSubmittedFileName();
				//Ϊ��ֹ�ϴ��ļ����������ļ�������
				System.out.println("�ļ���:"+fileName);
				String newFileName=System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
				//���ϴ����ļ������ڷ�������Ŀ������/imagesĿ¼��
				String filepath=getServletContext().getRealPath("/images");
				//String filepath2=getServletContext().getContextPath();
				System.out.println("ͷ�񱣴�·��Ϊ:"+filepath);
				File f=new File(filepath);
				if(!f.exists()) 
						f.mkdirs();
				part.write(filepath+"/"+newFileName);
				//�ӻỰ�л�õ�ǰ�û�������ʶ
				User user=(User)request.getSession().getAttribute("SESSION_USER");
				user.setHeadShot(newFileName);
				request.getSession().setAttribute("SESSION_USER", user);
				//��Ƭ���³ɹ���������ҳ
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
