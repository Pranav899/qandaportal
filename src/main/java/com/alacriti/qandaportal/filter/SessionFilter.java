package com.alacriti.qandaportal.filter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class SessionFilter implements ContainerRequestFilter{
		static Logger log = Logger.getLogger(SessionFilter.class);
		public static boolean SESSION;
		@Context HttpServletRequest httpServletRequest;
		public void filter(ContainerRequestContext context) throws IOException{
			URI uri = null;
			HttpSession session = httpServletRequest.getSession(false);
			if(session!=null){
				this.SESSION=true;
			}else{
				this.SESSION=false;
			}
			if(session == null && context.getUriInfo().getPath().contains("/add")){
				try {
					uri = new URI("http://localhost:8080/qandaportal/login.html");
					context.abortWith(Response.seeOther(uri).build());
				} catch (URISyntaxException e){
					log.info(e);
				}
			}
		}
}