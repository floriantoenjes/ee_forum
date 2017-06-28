package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.RegisterBean;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterController {

    @Inject
    private User user;

    @EJB
    private RegisterBean registerBean;

    public String register() {
        registerBean.register(user);

        return "index.xhtml";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
