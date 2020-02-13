package com.example.demo.filter;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFilter implements Filter {

    private RequestCache requestCache=new HttpSessionRequestCache();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        requestCache.saveRequest((HttpServletRequest) servletRequest,(HttpServletResponse) servletResponse);
        System.out.println("custom filter");

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
