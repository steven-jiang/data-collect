package com.kii.datacollect.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.common.base.Charsets;

import com.kii.datacollect.web.controller.AbstractController;
import com.kii.datacollect.web.controller.DataSubmitController;
import com.kii.datacollect.web.controller.SetTokenController;

public  class DataSubmitServlet extends HttpServlet {

	public  enum OperType {

		Upload(DataSubmitController.class),SetToken(SetTokenController.class);

		private Class<? extends AbstractController> cls;

		OperType(Class cls){
			this.cls=cls;
		}

		public Class<? extends AbstractController> getBusinessCls(){
			return cls;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());



		String  token=req.getHeader("Authorization");

		String context= StreamUtils.copyToString(req.getInputStream(), Charsets.UTF_8);

		String servletPath=req.getServletPath();

		String fullUrl=req.getRequestURI();

		String subType=fullUrl.substring(servletPath.length()+1+fullUrl.indexOf(servletPath));

		OperType operType= OperType.valueOf(StringUtils.capitalize(subType));

		try {

			AbstractController controller = ctx.getBean(operType.getBusinessCls());
			controller.doPost(context, token);
			resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			resp.setStatus(204);

		}catch(IllegalArgumentException ex){


			resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			resp.setStatus(500);
			resp.getWriter().write(ex.getMessage());
		}
		return;

	}
}
