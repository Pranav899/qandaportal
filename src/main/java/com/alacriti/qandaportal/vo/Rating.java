package com.alacriti.qandaportal.vo;

public class Rating{
	private long questionId;
	private long answerId;
	private int rating;
	private Long userId;
	public Rating(){
		
	}
	
	public Rating(long questionId, long answerId, int rating,
			 Long userId) {
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
	public int getRating(){
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
