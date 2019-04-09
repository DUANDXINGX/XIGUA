package com.dd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Dispose")
public class Dispose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String longurl = request.getParameter("long");
		String key = request.getParameter("key");
		String[] shorturl = Short.shortUrl(longurl,key);
		
		String long_n = JdbcSql.getLong(shorturl[0]);
		if(long_n == null) {
			User user = new User();
			user.setLongurl(longurl);
			user.setShorturl(shorturl[0]);
			user.setTime(0);
			JdbcSql.insert(user);
			long_n = longurl;
		}

		String str2 = "\"" + long_n + "\"";
		
		response.getWriter().write("<html>");
		response.getWriter().write("<head>");
		response.getWriter().write("<body>");
		response.getWriter().write("<div class='d2'>");
		response.getWriter().write("<a href=" + str2 +">localhost:8080/xigua/"+ shorturl[0] + "</a>");
		response.getWriter().write("</div>");
		response.getWriter().write("</body>");
		response.getWriter().write("</head>");
		response.getWriter().write("</html>");
	}


}
