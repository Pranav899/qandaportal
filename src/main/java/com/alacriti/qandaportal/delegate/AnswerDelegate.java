package com.alacriti.qandaportal.delegate;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.bo.AnswerLogics;
import com.alacriti.qandaportal.bo.QuestionLogics;
import com.alacriti.qandaportal.dao.AnswerDAO;
import com.alacriti.qandaportal.dao.QuestionDAO;
import com.alacriti.qandaportal.filter.SessionFilter;
import com.alacriti.qandaportal.vo.Answer;
import com.alacriti.qandaportal.vo.Question;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class AnswerDelegate {
	static Logger log = Logger.getLogger(AnswerDelegate.class);
	public static String getAnswersByRating(long questionId){
		List<Answer> answers = AnswerLogics.getAnswersByRating(questionId);
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setNumberFormat("0.######");
		configuration.setClassForTemplateLoading(AnswerDelegate.class,"/templates");
		Map input = new HashMap();
		Question question = QuestionLogics.getQuestion(questionId);
		boolean duplicate = QuestionDAO.checkDuplicateOrNot(questionId);
		Writer writer = new StringWriter();
		if(!answers.isEmpty() && duplicate != true){
				try {	
						Template template = configuration.getTemplate("answer.ftl");
						if(SessionFilter.SESSION == true){
							input.put("session", "present");
						}
						else{
							input.put("session", "absent");
						}
						input.put("question", question);
						input.put("answers", answers);
						template.process(input, writer);
				} catch (Exception e){
					log.info(e);
				}
		}
		else if(!answers.isEmpty() && duplicate == true){
			try {
					Template template = configuration.getTemplate("duplicate_question_answer.ftl");
					if(SessionFilter.SESSION == true){
						input.put("session", "present");
					}
					else{
						input.put("session", "absent");
					}
					input.put("question", question);
					input.put("answers", answers);
					template.process(input, writer);
			} catch (Exception e){
				log.info(e);
			}
		}
		else{
			try {
				if(duplicate != true){
					Template template = configuration.getTemplate("no_answer.ftl");
					if(SessionFilter.SESSION == true){
						input.put("session", "present");
					}
					else{
						input.put("session", "absent");
					}
					input.put("question", question);
					template.process(input, writer);
				}
				else{
					Template template = configuration.getTemplate("no_answer_duplicate.ftl");
					if(SessionFilter.SESSION == true){
						input.put("session", "present");
					}
					else{
						input.put("session", "absent");
					}
					input.put("question", question);
					template.process(input, writer);
				}
				} catch (Exception e){
						log.info(e);
				}
		}
		return writer.toString();
	}

	public static String addAnswerPage(long questionId){
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		configuration.setNumberFormat("0.######");
		Writer writer = new StringWriter();
		try {
				Template template = configuration.getTemplate("reply.ftl");
				Map input = new HashMap();
				Question question = QuestionLogics.getQuestion(questionId);
				input.put("question",question);
				template.process(input, writer);
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}
	
	public static String addAnswer(Answer answer){
		long status = AnswerDAO.addAnswer(answer);
		if(status == 1){
			return AnswerDelegate.getAnswersByRating(answer.getQuestionId());
		}
		else{
			return null;
		}
	}

	public static String getAnswersBySearch(String question, String searchInput) {
			List<Answer> answers = AnswerLogics.getAnswersBySearch(question,searchInput);
			Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
			configuration.setNumberFormat("0.######");
			configuration.setClassForTemplateLoading(AnswerDelegate.class,"/templates");
			Map input = new HashMap();
			Writer writer = new StringWriter();
			if(!answers.isEmpty()){
					try {
							Template template = configuration.getTemplate("answer_search_display.ftl");
							input.put("answers", answers);
							template.process(input, writer);
					} catch (Exception e){
						log.info(e);
					}
			}
			else{
				try {
					Template template = configuration.getTemplate("no_answer_search_found.ftl");
					template.process(input, writer);
					} catch (Exception e){
							log.info(e);
					}
			}
			return writer.toString();
	}

}
