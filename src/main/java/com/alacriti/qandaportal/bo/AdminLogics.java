package com.alacriti.qandaportal.bo;

import java.util.List;

import com.alacriti.qandaportal.dao.AdminDAO;
import com.alacriti.qandaportal.vo.Question;

public class AdminLogics {

	public static List<Question> getQuestionsForConfirming(){
		return AdminDAO.getQuestionsForConfirming();
	}

	public static long confirmStatus(long questionId, long parentId){
		return AdminDAO.confirmStatus(questionId,parentId);
	}

	public static long denyStatus(long questionId, long parentId){
		return AdminDAO.denyStatus(questionId,parentId);
	}
}
