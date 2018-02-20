package com.xiteng.bean;

import java.util.Date;

import com.xiteng.dao.NewsDao;

public class Comment {
	private int id;
	private int newsId;
	private int userId;
	private String nickname;
	private String content;
	private int favour;
	private int disfavour;
	private Date commentTime;
	private NewsDao dao=new NewsDao();
	public Comment() {
		super();
	}
	public Comment(int id) {
		super();
		this.id=id;
	}
	public Comment(int id,int newsId, int userId, String nickname, String content, int favour, int disfavour,
			Date commentTime) {
		super();
		this.id=id;
		this.newsId = newsId;
		this.userId = userId;
		this.nickname = nickname;
		this.content = content;
		this.favour = favour;
		this.disfavour = disfavour;
		this.commentTime = commentTime;
	}
	public Comment(int newsId, int userId, String nickname, String content, int favour, int disfavour,
			Date commentTime) {
		super();
		this.newsId = newsId;
		this.userId = userId;
		this.nickname = nickname;
		this.content = content;
		this.favour = favour;
		this.disfavour = disfavour;
		this.commentTime = commentTime;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFavour() {
		return favour;
	}
	public void setFavour(int favour) {
		this.favour = favour;
	}
	public int getDisfavour() {
		return disfavour;
	}
	public void setDisfavour(int disfavour) {
		this.disfavour = disfavour;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public void addComment() {
		// TODO Auto-generated method stub
		dao.addComment(newsId, userId, nickname, content, favour, disfavour, commentTime);
	}

	public void updateComment() {
		// TODO Auto-generated method stub
		dao.updateComment(id, newsId, userId, nickname, content, favour, disfavour, commentTime);
	}
	public void updateFavour(int favour) {
		// TODO Auto-generated method stub
		dao.updateFavour(id,favour);
	}
	public void updateDisfavour(int disfavour) {
		// TODO Auto-generated method stub
		dao.updateDisfavour(id,disfavour);
	}
	
}
