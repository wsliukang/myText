package com.xiteng.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xiteng.dao.NewsDao;

public class News {
	private int id;
	private String title;
	private String brief;
	private String content;
	private String author;
	private Date publishDate;
	private List<Comment>commentList=null;
	private NewsDao dao=new NewsDao();
	public News() {
		super();
	}
	public News(int id) {
		super();
		News news2=dao.getNewsById(id);
		this.id=news2.id;
		this.title = news2.title;
		this.brief = news2.brief;
		this.content = news2.content;
		this.author = news2.author;
		this.publishDate = news2.publishDate;
		commentList=dao.getCommentsByNewsId(id);
	}
	public News( String title, String brief, String content, String author, Date publishDate) {
		super();
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.author = author;
		this.publishDate = publishDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate=publishDate;
	}
	public void addNews() {
		// TODO Auto-generated method stub
		dao.addNews(title, brief, author, content, publishDate);
	}
	public void deleteNews() {
		// TODO Auto-generated method stub
		dao.deleteNews(id);
	}
	public void updateNews(String title, String brief, String content, String author, Date publishDate) {
		// TODO Auto-generated method stub
		dao.updateNews(id, title, brief, content, author, publishDate);
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.author = author;
		this.publishDate = publishDate;
	}
	public List<Comment> getComments(){
		return commentList;
	}

}
