package com.alacriti.qandaportal.vo;

public class Answer{
	
	private long answerId;
	private long questionId;
	private long userId;
	private String answer;
	public Answer(){
		
	}
	public Answer(long answerId, long questionId, long userId,String answer) {
		super();
		this.answerId = answerId;
		this.questionId = questionId;
		this.answer = answer;
		this.userId = userId;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}