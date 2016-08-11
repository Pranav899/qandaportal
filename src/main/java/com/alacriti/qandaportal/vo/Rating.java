package com.alacriti.qandaportal.vo;

public class Rating{
	private long questionId;
	private long answerId;
	private long rating;
	private long userId;
	public Rating(){
		
	}
	
	public Rating(long questionId, long answerId, long rating,
			 long userId) {
		super();
		this.questionId = questionId;
		this.answerId = answerId;
		this.rating = rating;
		this.userId = userId;
	}

	public long getQuestionId(){
		return questionId;
	}
	public void setQuestionId(long questionId){
		this.questionId = questionId;
	}
	public long getAnswerId(){
		return answerId;
	}
	public void setAnswerId(long answerId){
		this.answerId = answerId;
	}
	public long getRating(){
		return rating;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId){
		this.userId = userId;
	}
}
