package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.UserBean;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.ejb.EJB;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterController {

    @Inject
    private User user;

    @EJB
    private UserBean userBean;

    public String register() {
        user = userBean.register(user);

        FacesContext fc = FacesContext.getCurrentInstance();
        ELContext elContext = fc.getELContext();
        SignInController signInController = (SignInController) fc.getApplication().getELResolver()
                .getValue(elContext, null, "signinController");

        signInController.setUser(user);

        return "index.xhtml";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
