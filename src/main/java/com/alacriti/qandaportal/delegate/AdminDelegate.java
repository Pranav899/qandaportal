package com.alacriti.qandaportal.delegate;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.bo.AdminLogics;
import com.alacriti.qandaportal.bo.QuestionLogics;
import com.alacriti.qandaportal.resources.AdminResource;
import com.alacriti.qandaportal.vo.Question;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class AdminDelegate {
	static Logger log = Logger.getLogger(AdminDelegate.class);
	public static String getQuestionsForConfirming(){
		List<Question> questions = AdminLogics.getQuestionsForConfirming();
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		try {
			if(!questions.isEmpty()){
				Template template = configuration.getTemplate("admin_table.ftl");
				Map<String,List<Question>> input = new HashMap<String,List<Question>>();
				input.put("questions", questions);
				template.process(input, writer);
			}
			else{
				Template template = configuration.getTemplate("admin_table_no_update.ftl");
				Map<String,List<Question>> input = new HashMap<String,List<Question>>();
				template.process(input, writer);
			}
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}

	public static String getQuestionForView(long questionId) {
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
		Writer writer = new StringWriter();
		Question question = QuestionLogics.getQuestion(questionId);
		try {
				Template template = configuration.getTemplate("admin_question_view.ftl");
				Map input = new HashMap();
				input.put("question", question);
				template.process(input, writer);
		} catch (Exception e){
			log.info(e);
		}
		return writer.toString();
	}
}
