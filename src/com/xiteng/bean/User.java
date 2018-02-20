package com.xiteng.bean;

import java.util.List;

import com.xiteng.dao.UserDao;

public class User {
	private String username;
	private String password;
	private int id;
	private UserInfo userInfo=null;
	private String headShot;
	private List<Collection>collections=null;
	UserDao dao=new UserDao();
	public User(){}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.id=dao.login(username, password);
		userInfo=dao.getUserInfo(id);
		headShot=dao.getHeadShotByID(id);
		collections=dao.getCollectionsByUserId(id);
	}
	public User(int userId,String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.id=userId;
		userInfo=dao.getUserInfo(id);
		headShot=dao.getHeadShotByID(id);
		collections=dao.getCollectionsByUserId(id);
	}
	public boolean isExistUser() {
		return new UserDao().isExistUser(username);
	}
	public void save() {
		dao.save(username, password);
		id=dao.login(username, password);
		dao.AddHeadShot(id, "anyone.jpg");
		this.headShot="anyone.jpg";
		id=dao.login(username,password);
	}
	public void setHeadShot(String headShot) {
		dao.updateHeadShot(id, headShot);
		this.headShot=headShot;
	}
	public String getHeadShot() {
		return headShot;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void updateInfo(UserInfo userinfo) {
		this.userInfo=userinfo;
		if(dao.isExistUserInfo(id)) {
			dao.updateUserInfo(id, userInfo);
		}else {
			dao.AddUserInfo(id, userInfo);
		}
	}
	public void updateHeadShot(String headShot) {
		this.headShot=headShot;
		dao.updateHeadShot(id, headShot);
	}
	public List<Collection> getCollections() {
		return collections;
	}
	
	public boolean isBeCollected(int newsId) {
		Collection collection=dao.getCollectionsByUserIdAndNewsId(id,newsId);
		if(collection!=null&&collections.contains(collection))
			return true;
		else 
			return false;		
	}
	public void updateCollections() {
		// TODO Auto-generated method stub
		collections=dao.getCollectionsByUserId(id);
	}
	public boolean isAdmin() {
		if("admin".equals(username))
			return true;
		else
			return false;
	}
	
}
