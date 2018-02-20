package com.xiteng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiteng.bean.Collection;
import com.xiteng.bean.Comment;
import com.xiteng.bean.Picture;
import com.xiteng.bean.UserInfo;
import com.xiteng.util.DBUtil;

public class UserDao {
	public boolean isExistUser(String username) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from tb_user where username=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeJDBC(conn, pstmt, rs);
		}
		return false;
	}

	public void save(String username,String password) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		String sql="insert into tb_user(username,password) values"
				+ "(?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(conn, pstmt, null);
		}

	}
	
	public int login(String username,String password) {
		int userID=0;
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String sql="select Id from tb_user where username=? and password=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				userID=resultSet.getInt("Id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, resultSet);
		}
		return userID;
	}

	public void updateHeadShot(int userID, String newFileName) {
		// TODO Auto-generated method stub
		String sql="update tb_headshot "
				+ "set address=? "
				+ "where user_id=?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, newFileName);
			preparedStatement.setInt(2, userID);
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
	}

	public String getHeadShotByID(int userID) {
		// TODO Auto-generated method stub
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select address from tb_headshot where user_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("address");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeJDBC(conn, pstmt, rs);
		}
		return null;
	}

	public void load_pic(int userID, String address) {
		// TODO Auto-generated method stub
		String sql="insert into tb_pic(user_id,address) values"
				+ "(?,?)";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			preparedStatement.setString(2, address);
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
	}
	
	public List<Picture> getLoadPic(int userID){
		List<Picture>list=new ArrayList<Picture>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from tb_pic where user_id=? ";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Picture pic=new Picture();
				pic.setId(rs.getInt("Id"));
				pic.setUserId(rs.getInt("user_id"));
				pic.setAddress(rs.getString("address"));
				list.add(pic);
				}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		DBUtil.closeJDBC(conn, pstmt, rs);
	}
	return list;
	}
	
	public void updateUserInfo(int userID, UserInfo userInfo) {
		// TODO Auto-generated method stub
		String sql="update tb_userinfo "
				+ "set nickname=?,sex=?,age=?,telephone=?,address=?"
				+ "where user_id=?";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, userInfo.getNickName());
			preparedStatement.setString(2, userInfo.getSex());
			preparedStatement.setInt(3, userInfo.getAge());
			preparedStatement.setString(4, userInfo.getTelephone());
			preparedStatement.setString(5, userInfo.getAddress());
			preparedStatement.setInt(6, userID);
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
	}
	public void AddUserInfo(int userID, UserInfo userInfo) {
		// TODO Auto-generated method stub
		String sql="insert into tb_userinfo(nickname,sex,age,address,telephone,user_id) "
				+ "values (?,?,?,?,?,?)";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, userInfo.getNickName());
			preparedStatement.setString(2, userInfo.getSex());
			preparedStatement.setInt(3, userInfo.getAge());
			preparedStatement.setString(4, userInfo.getAddress());
			preparedStatement.setString(5, userInfo.getTelephone());
			preparedStatement.setInt(6, userID);
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
	}
	public UserInfo getUserInfo(int userID) {
		// TODO Auto-generated method stub
		String sql="select * from tb_userinfo "
				+ "where user_id=?";
		ResultSet rs=null;
		Connection connection=DBUtil.getConnection();
		UserInfo userinfo=null;
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			rs=preparedStatement.executeQuery();
			while(rs.next()) {
				userinfo=new UserInfo();
				userinfo.setNickName(rs.getString("nickname"));
				userinfo.setSex(rs.getString("sex"));
				userinfo.setAge(rs.getInt("age"));
				userinfo.setAddress(rs.getString("address"));
				userinfo.setTelephone(rs.getString("telephone"));
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
		return userinfo;
	}
	
	public boolean isExistUserInfo(int userId) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from tb_userinfo where user_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.closeJDBC(conn, pstmt, rs);
		}
		return false;
	}
	
	public void AddHeadShot(int userID, String headShot) {
		// TODO Auto-generated method stub
		String sql="insert into tb_headShot(user_id,address) "
				+ "values (?,?)";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			preparedStatement.setString(2, headShot);
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
	}

	public List<Collection> getCollectionsByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Collection>list=new ArrayList<Collection>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from tb_collection where user_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();	
			while(rs.next()) {
				Collection collection=new Collection();
				collection.setId(rs.getInt("Id"));
				collection.setNewsId(rs.getInt("news_id"));
				collection.setUserId(rs.getInt("user_id"));
				collection.setTitle(rs.getString("title"));
				collection.setBrief(rs.getString("brief"));
				list.add(collection);
				}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		DBUtil.closeJDBC(conn, pstmt, rs);
	}
	return list;
	}
	
	public void deleteCollectoinByNewsId(int newsId) {
		// TODO Auto-generated method stub
		String sql="delete from tb_collection where news_id=?";
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
	public void addCollection(int newsId,int userId,String title,String brief) {
		// TODO Auto-generated method stub
		String sql="insert into tb_collection(news_id,user_id,title,brief) values "
				+ "(?,?,?,?)";
		Connection connection=DBUtil.getConnection();
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, newsId);
			preparedStatement.setInt(2, userId);
			preparedStatement.setString(3, title);
			preparedStatement.setString(4, brief);
			preparedStatement.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(connection, preparedStatement, null);
		}
	}

	public Collection getCollectionsByUserIdAndNewsId(int userId, int newsId) {
		// TODO Auto-generated method stub
		Collection collection=null;
		Connection conn=DBUtil.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from tb_collection where user_id=? and news_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, newsId);
			rs=pstmt.executeQuery();	
			while(rs.next()) {
				collection=new Collection();
				collection.setId(rs.getInt("Id"));
				collection.setNewsId(rs.getInt("news_id"));
				collection.setUserId(rs.getInt("user_id"));
				collection.setTitle(rs.getString("title"));
				collection.setBrief(rs.getString("brief"));
				}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
		DBUtil.closeJDBC(conn, pstmt, rs);
	}
	return collection;
	}
}
