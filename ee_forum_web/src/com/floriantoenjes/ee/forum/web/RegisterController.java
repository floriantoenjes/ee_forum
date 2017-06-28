package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.RegisterBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterController {
    private String email;

    private String password;

    private String username;

    @EJB
    private RegisterBean registerBean;

    public String register() {
        registerBean.register(email, password, username);

        return "index.xhtml";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
