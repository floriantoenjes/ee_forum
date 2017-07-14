package com.floriantoenjes.ee.forum.web;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class SignInFilter implements Filter {

    @Inject
    private SignInController signInController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

        if ((path.startsWith("/thread_form") || path.startsWith("/post_form"))
                && signInController.getUser() == null) {

            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setStatus(401);
            servletRequest.getRequestDispatcher("/unauthorized.xhtml").forward(servletRequest, servletResponse);

        } else if ((path.matches("^/board/\\d+/thread/\\d+/edit$")
                || path.matches("^/board/\\d+/thread/\\d+/posts/\\d+$"))
                && signInController.getUser() == null) {

            servletRequest.getRequestDispatcher("/unauthorized.xhtml").forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
