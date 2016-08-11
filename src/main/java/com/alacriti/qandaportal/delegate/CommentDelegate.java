package com.alacriti.qandaportal.delegate;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.bo.AnswerLogics;
import com.alacriti.qandaportal.bo.CommentLogics;
import com.alacriti.qandaportal.bo.QuestionLogics;
import com.alacriti.qandaportal.dao.QuestionDAO;
import com.alacriti.qandaportal.filter.SessionFilter;
import com.alacriti.qandaportal.vo.Answer;
import com.alacriti.qandaportal.vo.Comment;
import com.alacriti.qandaportal.vo.Question;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CommentDelegate {
	static Logger log = Logger.getLogger(CommentDelegate.class);
	public static String getComments(long questionId, long answerId) {
		List<Comment> comments = CommentLogics.getComments(questionId,answerId);
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(AnswerDelegate.class,"/templates");
		Map input = new HashMap();
		Question question = QuestionLogics.getQuestion(questionId);
		Answer answer = AnswerLogics.getAnswer(questionId, answerId);
		Writer writer = new StringWriter();
		if(!comments.isEmpty()){
			try {
					Template template = configuration.getTemplate("comments.ftl");
					if(SessionFilter.SESSION == true){
						input.put("session", "present");
					}
					else{
						input.put("session", "absent");
					}
					input.put("question", question);
					boolean duplicate = QuestionDAO.checkDuplicateOrNot(questionId);
					if(duplicate == true){
						input.put("duplicate","yes");
					}
					else{
						input.put("duplicate","no");
					}
					input.put("answer", answer);
					input.put("comments", comments);
					template.process(input, writer);
			} catch (Exception e){
				log.info(e);
			}
		}
		else{
			try {
				Template template = configuration.getTemplate("no_comments.ftl");
				if(SessionFilter.SESSION == true){
					input.put("session", "present");
				}
				else{
					input.put("session", "absent");
				}
				boolean duplicate = QuestionDAO.checkDuplicateOrNot(questionId);
				if(duplicate == true){
					input.put("duplicate","yes");
				}
				else{
					input.put("duplicate","no");
				}
				input.put("question", question);
				input.put("answer", answer);
				template.process(input, writer);
		} catch (Exception e){
			log.info(e);
		}
		}
		return writer.toString();
	}

}
