package no.hvl.dat152.controllers;
							
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import com.google.gson.Gson;

import no.hvl.dat152.model.ErrorMessage;
import no.hvl.dat152.repository.JsonUtil;
					
@WebFilter("/api/*")
public class UtilityFilter implements Filter {
	
	private List<String> legalMethods;
	private Gson gson;
    
	@SuppressWarnings("deprecation")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String method = request.getMethod();
		
		
		for (String md : legalMethods) {
			if (md.equals(method)) {		
				
				String[] params = request.getPathInfo().split("/");
				String id = params[params.length-1];
				System.out.println("id: " + id);
				if (NumberUtils.isNumber(id)) {
					req.setAttribute("updateid", id);
				}

				chain.doFilter(req, res);
				return;
			}
		} 
		
		ErrorMessage em = new ErrorMessage().withStatusCode(405).withDescription("method not allowed");
		String errorJson = gson.toJson(em);
		JsonUtil.sendJson(errorJson, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
		legalMethods = Arrays.asList("GET", "POST", "PUT");
		gson = new Gson();
	}

}
