package com.alacriti.qandaportal.delegate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alacriti.qandaportal.bo.LoginLogics;
import com.alacriti.qandaportal.dao.LoginDAO;
import com.alacriti.qandaportal.filter.SessionFilter;
import com.alacriti.qandaportal.filter.SessionUtilities;
import com.alacriti.qandaportal.vo.Login;
import com.alacriti.qandaportal.vo.Question;
import com.alacriti.qandaportal.vo.User;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class LoginDelegate {
	static Logger log = Logger.getLogger(LoginDelegate.class);
	public static String validateLogin(String userName,String password,HttpServletRequest request){
			Login login = null;
				login = new Login(userName,password);
			boolean valid =  LoginLogics.validateLogin(login);
			Writer writer = new StringWriter();
			if(valid == true){
				User user = LoginDAO.getUserData(userName);
				User validUser = new User(user.getUserId(),user.getUserName(),user.getFullName(),user.getEmail(),user.getRole(),true);
				SessionUtilities.createSession(request,validUser);
				Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
				configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
				try {
						User sessionUser = (User) request.getSession().getAttribute("sessionObject");
						if(sessionUser.getRole().equals("user")){
							Template template = configuration.getTemplate("default.ftl");
							Map input = new HashMap();
							input.put("session", "present");
							template.process(input, writer);
						}
						else if(sessionUser.getRole().equals("admin")){
							Template template = configuration.getTemplate("admin.ftl");
							Map<String,List<Question>> input = new HashMap<String,List<Question>>();
							template.process(input, writer);
						}
				} catch (Exception e){
					log.info(e);
				}
			}
			return writer.toString();
	}
	public static String getHomePage(HttpServletRequest request){
		Writer writer = new StringWriter();
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
		configuration.setClassForTemplateLoading(QuestionDelegate.class,"/templates");
		Template template;
		try {
			template = configuration.getTemplate("default.ftl");
			Map input = new HashMap();
			if(SessionFilter.SESSION == true){
				input.put("session", "present");
			}
			else{
				input.put("session", "absent");
			}
			template.process(input, writer);
		} catch (Exception e){
			log.info(e);
		} 
		return writer.toString();
	}
	
	private static final String CLIENT_ID = "391896005530-gq3r4a7fos374vt2imgb097mrdsge4k0.apps.googleusercontent.com";
	private static final HttpTransport transport = new NetHttpTransport();
	private static final JsonFactory jsonFactory = new JacksonFactory();
	
	public static String googleLogin(String tokenId,HttpServletRequest request) {
		Writer writer = new StringWriter();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
	    .setAudience(Arrays.asList(CLIENT_ID))
	    .setIssuer("accounts.google.com")
	    .build();
		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(tokenId);
		} catch (GeneralSecurityException e) {
			log.info(e);
		} catch (IOException e) {
			log.info(e);
		}
		if(idToken != null){
			Payload payload = idToken.getPayload();
			String userId = payload.getSubject();
			String email = payload.getEmail();
			boolean emailVerified = payload.getEmailVerified();
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			if(emailVerified == true){
				long result = LoginDAO.AddGoogleUserData(givenName,name, email);
				if(result == 1){
					User user = LoginDAO.getUserData(givenName);
					User validUser = new User(user.getUserId(),user.getUserName(),user.getFullName(),user.getEmail(),user.getRole(),true);
					SessionUtilities.createSession(request,validUser);
					Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
					configuration.setClassForTemplateLoading(QuestionDelegate.class, "/templates");
					Template template;
					try {
						template = configuration.getTemplate("default.ftl");
						Map input = new HashMap();
						input.put("session", "present");
						template.process(input, writer);
					} catch (Exception e){
						log.info(e);
					} 
				}
			}
		}
		else{
			log.info("Invalid TokenId");
		}
		return writer.toString();
	}
}