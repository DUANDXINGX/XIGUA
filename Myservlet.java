package com.dd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Myservlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		response.getWriter().write("<html>");
		response.getWriter().write("<head>");
		response.getWriter().write("<body>");
		response.getWriter().write("<form action='login' method='post'>");
		response.getWriter().write("Ô­ÍøÖ·:<input type='text' name='long' value=''/><br/>");
		response.getWriter().write("key:<input type='text' name='key' value=''/><br/>");
		response.getWriter().write("<input type='submit' value='È·ÈÏ'/><br/>");
		response.getWriter().write("</form>");
		response.getWriter().write("</body>");
		response.getWriter().write("</heda>");
		response.getWriter().write("</html>");
		
	}

}
