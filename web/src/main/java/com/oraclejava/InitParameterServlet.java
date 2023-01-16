package com.oraclejava;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitParameterServlet extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("당신의 나이는 " + config.getInitParameter("age"));
		System.out.println("주소는"+ config.getInitParameter("address"));
		System.out.println("Your hobby is "+ config.getInitParameter("hobby")+"~!");
	}
}
