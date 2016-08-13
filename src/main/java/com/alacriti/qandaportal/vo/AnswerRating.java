package com.alacriti.qandaportal.vo;

public class AnswerRating {
	private long positiveRating;
	private long negativeRating;
	public long getPositiveRating() {
		return positiveRating;
	}
	public void setPositiveRating(long positiveRating) {
		this.positiveRating = positiveRating;
	}
	public AnswerRating(long positiveRating, long negativeRating) {
		super();
		this.positiveRating = positiveRating;
		this.negativeRating = negativeRating;
	}
	public long getNegativeRating() {
		return negativeRating;
	}
	public void setNegativeRating(long negativeRating) {
		this.negativeRating = negativeRating;
	}
}
