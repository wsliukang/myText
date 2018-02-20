package com.xiteng.bean;

import com.xiteng.dao.NewsDao;
import com.xiteng.dao.UserDao;

public class Collection {
	private int id;
	private int userId;
	private int newsId;
	private String title;
	private String brief;
	private NewsDao newsDao=new NewsDao();
	private UserDao userDao=new UserDao();
	public Collection() {
		super();
	}
	
	public Collection(int userId, int newsId) {
		super();
		this.userId = userId;
		this.newsId = newsId;
		News news=newsDao.getNewsById(newsId);
		this.title = news.getTitle();
		this.brief = news.getBrief();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}

	public void addCollection() {
		// TODO Auto-generated method stub
		userDao.addCollection(newsId, userId, title, brief);
	}

	public void deleteCollection() {
		// TODO Auto-generated method stub
		userDao.deleteCollectoinByNewsId(newsId);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.getId()+this.getTitle().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj)
		{
			return true;
		}
		if(obj instanceof Collection)
		{
			Collection i = (Collection)obj;
			if(this.getId()==i.getId())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
}
