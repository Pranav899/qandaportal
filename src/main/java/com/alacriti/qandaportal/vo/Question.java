package com.alacriti.qandaportal.vo;

public class Question {
	private long questionId;
	private long userId;
	private String question;
	private String dateAdded;
	private long parentQuestionId;
	private String topic1;
	private String topic2;
	private String topic3;
	private long numberOfAnswers;
	private long views;
	
	
	public Question(long questionId, long userId, String question,
			String dateAdded, long parentQuestionId, String topic1,
			String topic2, String topic3, long numberOfAnswers,long views){
		super();
		this.questionId = questionId;
		this.userId = userId;
		this.question = question;
		this.dateAdded = dateAdded;
		this.parentQuestionId = parentQuestionId;
		this.topic1 = topic1;
		this.topic2 = topic2;
		this.topic3 = topic3;
		this.numberOfAnswers = numberOfAnswers;
		this.views = views;
	}
	public Question(long userId,String question,String dateAdded,String topic1,
			String topic2, String topic3){
		this.userId = userId;
		this.question = question;
		this.dateAdded = dateAdded;
		this.topic1 = topic1;
		this.topic2 = topic2;
		this.topic3 = topic3;
	}
	public long getViews(){
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public Question(){
	}
	public long getNumberOfAnswers() {
		return numberOfAnswers;
	}
	public void setNumberOfAnswers(long numberOfAnswers) {
		this.numberOfAnswers = numberOfAnswers;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserName(long userId) {
		this.userId = userId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded){
		this.dateAdded = dateAdded;
	}
	public long getParentQuestionId() {
		return parentQuestionId;
	}
	public void setParentQuestionId(long parentQuestionId) {
		this.parentQuestionId = parentQuestionId;
	}
	public String getTopic1() {
		return topic1;
	}
	public void setTopic1(String topic1) {
		this.topic1 = topic1;
	}
	public String getTopic2() {
		return topic2;
	}
	public void setTopic2(String topic2) {
		this.topic2 = topic2;
	}
	public String getTopic3() {
		return topic3;
	}
	public void setTopic3(String topic3) {
		this.topic3 = topic3;
	}
}