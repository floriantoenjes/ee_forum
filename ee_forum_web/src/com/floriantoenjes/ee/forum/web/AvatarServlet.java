package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AvatarServlet", urlPatterns = "/avatar")
public class AvatarServlet extends HttpServlet {

    @EJB
    private UserBean userBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        byte[] avatar = userBean.getAvatar(userId);
        if (avatar != null) {
            response.reset();
            response.getOutputStream().write(avatar);
        }
    }
}
