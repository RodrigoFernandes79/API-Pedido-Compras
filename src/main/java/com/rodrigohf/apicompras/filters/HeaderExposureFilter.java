package com.rodrigohf.apicompras.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

//Classe que possui metodo para expor os endpoints de post e put para a aplicação frontend poder acessá los

@Component
public class HeaderExposureFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader("access-control-expose-headers", "Location");// resposta do location put e post validas
		// pra outras requisiçoes Http de apis(frontend)
		chain.doFilter(request, response);

	}

}
