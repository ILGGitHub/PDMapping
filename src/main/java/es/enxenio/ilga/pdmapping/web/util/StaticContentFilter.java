package es.enxenio.ilga.pdmapping.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class StaticContentFilter implements Filter {

	private static final String DEFAULT_SERVLET_NAME = "default";
	private ServletContext servletContext;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws ServletException, IOException {

		servletContext.getNamedDispatcher(DEFAULT_SERVLET_NAME).forward(req,
				resp);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {

		servletContext = config.getServletContext();
	}

}
