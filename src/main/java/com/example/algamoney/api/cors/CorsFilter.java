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

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	
	//@Autowired
	//private SalvavidasApiProperty salvavidasApiProperty;	
	
	private String originPermitida = "https://salvavidas-ui.herokuapp.com"; //"http://localhost:4200"; // TODO: configurar para ambientes diferentes
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {		
			
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;		
		
		response.setHeader("Access-Control-Allow-Origin", originPermitida );  //  salvavidasApiProperty.getOriginPermitida()
		response.setHeader("Access-Control-Allow-Credentials", "true");					
		
		if ("OPTIONS".equals(request.getMethod()) && originPermitida.equals(request.getHeader("Origin"))) {
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS"); // salvavidasApiProperty.getOriginPermitida()			
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

/*
@Override
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
           throws IOException, ServletException {

       HttpServletRequest request = (HttpServletRequest) req;
       HttpServletResponse response = (HttpServletResponse) res;

       if (request.getHeader("Origin") != null) {
           response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
       }

       response.setHeader("Access-Control-Allow-Credentials", "true");
       response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
       response.setHeader("Access-Control-Max-Age", "3600");
       response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

       chain.doFilter(req, res);
   } */