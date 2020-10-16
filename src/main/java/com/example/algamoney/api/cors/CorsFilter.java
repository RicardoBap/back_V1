package com.example.algamoney.api.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.algamoney.api.config.property.SalvavidasApiProperty;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	
	@Autowired
	private SalvavidasApiProperty salvavidasApiProperty;
	
	private String originPermitida =  "https://salvavidas-ui.herokuapp.com";
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		//String originPermitida = "http://localhost:4200"; //TODO: Configurar para diferentes ambientes		
		//String originPermitida = "https://salvavidas-ui.herokuapp.com";
			
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//response.setHeader("Access-Control-Allow-Origin", originPermitida);		
		response.setHeader("Access-Control-Allow-Origin", salvavidasApiProperty.getOriginPermitida());
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
		response.setHeader("Access-Control-Max-Age", "3600");		
		
		//if ("OPTIONS".contentEquals(request.getMethod()) && originPermitida.contentEquals(request.getHeader("Origin")) ) {
		if ("OPTIONS".equals(request.getMethod()) && salvavidasApiProperty.getOriginPermitida().equals(request.getHeader("Origin")) ) {	
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
			response.setHeader("Access-Control-Max-Age", "3600");
			
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, resp);
		}
		
	}
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}	

	@Override
	public void destroy() {}

}
