package com.alacriti.qandaportal.vo;

public class Comment {
	private long questionId;
	private long answerid;
	private long commentId;
	private String comment;
	private long userId;
	public Comment(){
		
	}
	public Comment(long commentId, long answerId, long questionId,long userId,String comment){
		super();
		this.questionId = questionId;
		this.answerid = answerId;
		this.commentId = commentId;
		this.comment = comment;
		this.userId = userId;
	}
	
	public Comment(long answerId, long questionId,long userId,String comment){
		super();
		this.questionId = questionId;
		this.answerid = answerId;
		this.comment = comment;
		this.userId = userId;
	}
	
	public long getQuestionId(){
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public long getAnswerId() {
		return answerid;
	}
	public void setAnswerId(long answerid) {
		this.answerid = answerid;
	}
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public long getuserId() {
		return userId;
	}
	public void setuserId(long userId){
		this.userId = userId;
	}
}
