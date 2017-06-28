package com.floriantoenjes.ee.forum.ejb;

import com.floriantoenjes.ee.forum.ejb.model.Role;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class RegisterBean {

    @PersistenceContext
    private EntityManager em;

    public RegisterBean() {

    }

    public String register(String email, String password, String username) {
        Query query = em.createNamedQuery("Role.findByName");
        query.setParameter("name", "USER");
        Role userRole = (Role) query.getSingleResult();

        User user = new User();
        user.addRole(userRole);
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);

        em.persist(user);

        return "Registration successful!";
    }
}
