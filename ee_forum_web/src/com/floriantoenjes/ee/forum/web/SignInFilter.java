package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.ThreadBean;
import com.floriantoenjes.ee.forum.ejb.model.Thread;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class SignInFilter implements Filter {

    @Inject
    private SignInController signInController;

    @EJB
    private ThreadBean threadBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());

        Pattern threadPattern = Pattern.compile("^/board/\\d+/thread/(\\d+)/edit$");
        Matcher threadMatcher = threadPattern.matcher(path);

        if ((path.startsWith("/thread_form") || path.startsWith("/post_form"))
                && signInController.getUser() == null) {

            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setStatus(401);
            servletRequest.getRequestDispatcher("/unauthorized.xhtml").forward(servletRequest, servletResponse);

        // Check if user is author of thread
        } else if (threadMatcher.find()) {

            Long threadId = Long.parseLong(threadMatcher.group(1));
            Thread thread = threadBean.find(threadId);

            if (signInController.getUser() == null || !signInController.getUser().getUsername().equals(thread.getAuthor().getUsername())) {
                servletRequest.getRequestDispatcher("/unauthorized.xhtml").forward(servletRequest, servletResponse);
            }

        // Check if user is author of post
        } else if (path.matches("^/board/\\d+/thread/\\d+/posts/\\d+$") && signInController.getUser() == null) {

            servletRequest.getRequestDispatcher("/unauthorized.xhtml").forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
