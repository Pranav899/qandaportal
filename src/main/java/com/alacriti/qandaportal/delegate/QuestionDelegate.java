package com.alacriti.qandaportal.delegate;


import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.bo.QuestionLogics;
import com.alacriti.qandaportal.filter.SessionFilter;
import com.alacriti.qandaportal.vo.Page;
import com.alacriti.qandaportal.vo.Question;
import com.alacriti.qandaportal.vo.QuestionPage;

import freemarker.template.Configuration;
import freemarker.template.Template;


public class QuestionDelegate{
	static Logger log = Logger.getLogger(QuestionDelegate.class);
	
	public static String addQuestionPage(){
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		try {
				Template template = configuration.getTemplate("addquestion.ftl");
				Map input = new HashMap();
				template.process(input, writer);
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}
	
	public static String addQuestion(Question question){
		long questionId = QuestionLogics.addQuestion(question);
		return AnswerDelegate.getAnswersByRating(questionId);
	}
	
	public static String getRecentlyAskedQuestions(){
		QuestionPage questionPage = QuestionLogics.getRecentlyAskedQuestions();
		List<Page> pages = questionPage.getPages();
		List<Question> questions = questionPage.getQuestions();
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		try {
			if(!questions.isEmpty()){
				Template template = configuration.getTemplate("pagination_question.ftl");
				Map input = new HashMap();
				input.put("pages", pages);
				input.put("questions", questions);
				template.process(input, writer);
			}
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}
	
	public static String getQuestionsByTopic(String searchItem){
		List<Question> questions = QuestionLogics.getQuestionsByTopic(searchItem);
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		try {
			if(!questions.isEmpty()){
					Template template = configuration.getTemplate("searchQuestion.ftl");
					Map input = new HashMap();
					if(SessionFilter.SESSION == true){
						input.put("session", "present");
					}
					else{
						input.put("session", "absent");
					}
					input.put("questions", questions);
					template.process(input, writer);
			}
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}
	
	public static String getQuestionsBySearch(String searchItem){
		List<Question> questions = QuestionLogics.getQuestionsByTopic(searchItem);
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		try {
			if(!questions.isEmpty()){
					Template template = configuration.getTemplate("searchbar.ftl");
					Map<String,List<Question>> input = new HashMap<String,List<Question>>();
					input.put("questions", questions);
					template.process(input, writer);
			}
			else{
				Template template = configuration.getTemplate("searchbar_no_questions_found.ftl");
				Map<String,List<Question>> input = new HashMap<String,List<Question>>();
				template.process(input, writer);
			}
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}
	
	public static String getMostViewedQuestions(){
		List<Question> questions = QuestionLogics.getMostViewedQuestions();
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		try {
			if(!questions.isEmpty()){
				Template template = configuration.getTemplate("most.ftl");
				Map<String,List<Question>> input = new HashMap<String,List<Question>>();
				input.put("questions", questions);
				template.process(input, writer);
			}
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}
	
	public static String getRecentlyAskedQuestionsForSideView(){
		List<Question> questions = QuestionLogics.getRecentlyAskedQuestionForSideView();
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		try {
			if(!questions.isEmpty()){
				Template template = configuration.getTemplate("recent-questions-side-view.ftl");
				Map<String,List<Question>> input = new HashMap<String,List<Question>>();
				input.put("questions", questions);
				template.process(input, writer);
			}
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}
	
	public static String getQuestionsByUnAnswered(){
		List<Question> questions = QuestionLogics.getQuestionsByUnAnswered();
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		try {
			if(!questions.isEmpty()){
					Template template = configuration.getTemplate("unanswered.ftl");
					Map input = new HashMap();
					if(SessionFilter.SESSION == true){
						input.put("session", "present");
					}
					else{
						input.put("session", "absent");
					}
					input.put("questions", questions);
					template.process(input, writer);
			}
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}

	public static String addParentIdForQuestion(long questionId, long parentId){
		long result = QuestionLogics.addParentIdForQuestion(questionId,parentId);
		if(result == 1){
			return AnswerDelegate.getAnswersByRating(questionId);
		}
		else{
			return null;
		}
	}

	public static String getRecentlyAddedQuestions(long uniqueId, long start,long noOfPages) {
		QuestionPage questionPage = QuestionLogics.getRecentlyAskedQuestions(uniqueId,start,noOfPages);
		List<Question> questions = questionPage.getQuestions();
		List<Page> pages = questionPage.getPages();
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		try {
			if(!questions.isEmpty()){
				Template template = configuration.getTemplate("pagination_question.ftl");
				Map input = new HashMap();
				input.put("pages", pages);
				input.put("questions", questions);
				template.process(input, writer);
			}
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}
}
