package com.xiteng.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

//import com.sun.prism.Image;

/**
 * Servlet implementation class ValidateCodeServlet
 */
@WebServlet("/ValidateCodeServlet")
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//������ӦͷContent-type����
		response.setContentType("image/jpeg");
		//��ȡ�������������������
		ServletOutputStream out=response.getOutputStream();
		//��������ͼ��
		int width=60;
		int height=20;
		BufferedImage imgbuf=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g=imgbuf.createGraphics();
		//�趨����ɫ
		g.setColor(getRandColor(200,250));
		//�趨ͼ����״�Ϳ��
		g.fillRect(0, 0, width, height);
		//�������100��������
		Random r=new Random();
		g.setColor(getRandColor(160, 200));
		for(int i=0;i<100;i++) {
			int x=r.nextInt(width);
			int y=r.nextInt(height);
			int x1=r.nextInt(12);
			int y1=r.nextInt(12);
			g.drawLine(x, y, x+x1, y+y1);
		}
		//�������100�����ŵ�
		g.setColor(getRandColor(120, 240));
		for(int i=0;i<100;i++) {
			int x=r.nextInt(width);
			int y=r.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}
		//�������0~9֮���4λ����֤��
		g.setFont(new Font("Time New Roman", Font.PLAIN, 18));
		String code="";
		for(int i=0;i<4;i++) {
			String rand=String.valueOf(r.nextInt(10));
			code+=rand;
			g.setColor(new Color(20+r.nextInt(110), 20+r.nextInt(110), 20+r.nextInt(110)));
			g.drawString(rand, 13*i+6, 16);
		
		}
	
		//���ͼ��
		
		ImageIO.write(imgbuf, "JPEG", out);
		request.getSession().setAttribute("validateCode", code);
		out.close();
	}
	//��ȡָ����Χ�����ɫ
	private Color getRandColor(int fc, int bc) {
		// TODO Auto-generated method stub
		Random random=new Random();
		if(fc>255)
			fc=255;
		if(fc<0)
			fc=0;
		if(bc>255)
			bc=255;
		if(bc<0)
			bc=0;
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r, g, b);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
