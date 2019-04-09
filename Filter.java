package com.dd;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/Filter")
public class Filter implements javax.servlet.Filter {

    public Filter() {
    }

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String url  = ((HttpServletRequest)request).getServletPath();
		
		String str_url = url.replace("/","");
		String long_n = JdbcSql.getLong(str_url);
		
		if(long_n != null) {
			try {
				String ip = request.getRemoteAddr();
				int time = JdbcSql.getLong_visit(str_url);
				if(time < 0) {
					JdbcSql.insert_visit(ip, str_url, 0);
				}
				else {
					time = time + 1;
					JdbcSql.update_visit(time,ip, str_url);
				}
					
				((HttpServletResponse)response).sendRedirect(long_n);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}
