package com.xiteng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiteng.bean.*;
import com.xiteng.util.DBUtil;


public class NewsDao {
	public void addNews(String title,String brief,String author,String content,Date publish_date) {
		// TODO Auto-generated method stub
		String sql="insert into tb_news(title,brief,author,content,publish_date) values "
				+ "(?,?,?,?,?)";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, brief);
			preparedStatement.setString(3, author);
			preparedStatement.setString(4, content);
			preparedStatement.setTimestamp(5, new Timestamp(publish_date.getTime()));
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
	}
	public void deleteNews(int newsId) {
		// TODO Auto-generated method stub
		String sql="delete from tb_news where Id=?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, newsId);
			preparedStatement.executeUpdate();
			//删除新闻下评论
			deleteCommentsByNewsId(newsId);
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
	}
	public void updateNews(int newsId,String title,String brief,String content,String author,Date publish_date) {
		// TODO Auto-generated method stub
		String sql="update tb_news set title=?,brief=?,content=?,author=?,publish_date=?"
				+ "where Id=?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, brief);
			preparedStatement.setString(3, content);
			preparedStatement.setString(4, author);
			preparedStatement.setTimestamp(5, new Timestamp(publish_date.getTime()));
			preparedStatement.setInt(6, newsId);
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
	}
	
	public List<News> getAllNews(){
		List<News>list=new ArrayList<News>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from tb_news";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();	
			while(rs.next()) {
				News news=new News();
				news.setId(rs.getInt("Id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setBrief(rs.getString("brief"));
				news.setAuthor(rs.getString("author"));
				news.setPublishDate(rs.getTimestamp("publish_date"));
				list.add(news);
				}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		DBUtil.closeJDBC(conn, pstmt, rs);
	}
	return list;
	}
	public News getNewsById(int id){
		News news=null;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from tb_news where Id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();	
			while(rs.next()) {
				news=new News();
				news.setId(rs.getInt("Id"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setBrief(rs.getString("brief"));
				news.setAuthor(rs.getString("author"));
				news.setPublishDate(rs.getTimestamp("publish_date"));
				}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		DBUtil.closeJDBC(conn, pstmt, rs);
	}
	return news;
	}
	
	public ArrayList<News> getHistoryList(String list)
	{
		ArrayList<News> itemlist = new ArrayList<News>();
		int iCount=5; //每次返回前五条记录
		if(list!=null&&list.length()>0)
		{
		    String[] arr = list.split(",");
		   
		    //如果商品记录大于等于5条
		    if(arr.length>=10)
		    {
		       for(int i=arr.length-1;i>=arr.length-iCount;i--)
		       {
		    	  itemlist.add(getNewsById(Integer.parseInt(arr[i])));  
		       }
		    }
		    else
		    {
		    	for(int i=arr.length-1;i>=0;i--)
		    	{
		    		itemlist.add(getNewsById(Integer.parseInt(arr[i])));
		    	}
		    }
		    return itemlist;
		}
		else
		{
			return null;
		}
	}
		public void addComment(int newsId,int userId,String nickname,String content,int favour,int disfavour,Date commentTime) {
			// TODO Auto-generated method stub
			String sql="insert into tb_comment(news_id,user_id,content,favour,disfavour,comment_time,nickname) values "
					+ "(?,?,?,?,?,?,?)";
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=null;
			try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, newsId);
				preparedStatement.setInt(2, userId);
				preparedStatement.setString(3, content);
				preparedStatement.setInt(4, favour);
				preparedStatement.setInt(5, disfavour);
				preparedStatement.setTimestamp(6, new Timestamp(commentTime.getTime()));
				preparedStatement.setString(7, nickname);
				preparedStatement.executeUpdate();
			} 
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DBUtil.closeJDBC(connection, preparedStatement, null);
			}
		}
		
		public void updateComment(int id,int newsId,int userId,String nickname,String content,int favour,int disfavour,Date commentTime) {
			// TODO Auto-generated method stub
			String sql="update tb_comment set news_id=?,user_id=?,content=?,favour=?,disfavour=?,comment_time=?,nickname=? "
					+ " where Id=? ";
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=null;
			try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, newsId);
				preparedStatement.setInt(2, userId);
				preparedStatement.setString(3, content);
				preparedStatement.setInt(4, favour);
				preparedStatement.setInt(5, disfavour);
				preparedStatement.setTimestamp(6, new Timestamp(commentTime.getTime()));
				preparedStatement.setString(7, nickname);
				preparedStatement.setInt(8, userId);
				preparedStatement.executeUpdate();
			} 
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DBUtil.closeJDBC(connection, preparedStatement, null);
			}
		}
		
		public List<Comment> getCommentsByNewsId(int newsId){
			List<Comment>list=new ArrayList<Comment>();
			Connection conn=DBUtil.getConnection();
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="select * from tb_comment where news_id=?";
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, newsId);
				rs=pstmt.executeQuery();	
				while(rs.next()) {
					Comment comment=new Comment();
					comment.setId(rs.getInt("Id"));
					comment.setNewsId(rs.getInt("news_id"));
					comment.setUserId(rs.getInt("user_id"));
					comment.setContent(rs.getString("content"));
					comment.setFavour(rs.getInt("favour"));
					comment.setNickname(rs.getString("nickname"));
					comment.setDisfavour(rs.getInt("disfavour"));
					comment.setCommentTime(rs.getTimestamp("comment_time"));
					list.add(comment);
					}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
			DBUtil.closeJDBC(conn, pstmt, rs);
		}
		return list;
		}
		
		public void deleteCommentsByNewsId(int newsId) {
			// TODO Auto-generated method stub
			String sql="delete from tb_comment where news_id=?";
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=null;
			try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, newsId);
				preparedStatement.executeUpdate();
			} 
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DBUtil.closeJDBC(connection, preparedStatement, null);
			}
		}
		public void updateFavour(int id,int favour) {
			// TODO Auto-generated method stub
			String sql="update tb_comment set favour=? "
					+ " where Id=? ";
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=null;
			try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, favour);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			} 
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DBUtil.closeJDBC(connection, preparedStatement, null);
			}
		}
		public void updateDisfavour(int id, int disfavour) {
			// TODO Auto-generated method stub
			String sql="update tb_comment set disfavour=? "
					+ " where Id=? ";
			Connection connection=DBUtil.getConnection();
			PreparedStatement preparedStatement=null;
			try {
				preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setInt(1, disfavour);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			} 
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DBUtil.closeJDBC(connection, preparedStatement, null);
			}
		}
}
