package com.alacriti.qandaportal.bo;

import com.alacriti.qandaportal.dao.RatingDAO;
import com.alacriti.qandaportal.vo.Rating;

public class RatingLogics {
	
	public static long getSumOfPositiveRating(long questionId,long answerId){
		return RatingDAO.getSumOfPositiveRating(questionId, answerId);
	}
	public static long getSumOfNegativeRating(long questionId,long answerId){
		return RatingDAO.getSumOfNegativeRating(questionId, answerId);
	}
	
	public static void addRating(Rating rating){
		RatingDAO.addRating(rating);
	}
}
