package com.alacriti.qandaportal.bo;

import javax.servlet.http.HttpServletRequest;

import com.alacriti.qandaportal.dao.RatingDAO;
import com.alacriti.qandaportal.vo.Rating;
import com.alacriti.qandaportal.vo.User;

public class RatingLogics {
	public static long getSumOfPositiveRating(long questionId,long answerId){
		return RatingDAO.getSumOfPositiveRating(questionId, answerId);
	}
	public static long getSumOfNegativeRating(long questionId,long answerId){
		return RatingDAO.getSumOfNegativeRating(questionId, answerId);
	}
	public static void addRating(long questionId,long answerId,long rating, HttpServletRequest request){
		User sessionUser = (User) request.getSession().getAttribute("sessionObject");
		Rating rating_object = new Rating(questionId,answerId,rating,sessionUser.getUserId());
		RatingDAO.addRating(rating_object);
	}
}