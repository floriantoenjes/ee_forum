package com.floriantoenjes.ee.forum.ejb;

import com.floriantoenjes.ee.forum.ejb.model.Role;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

@Stateless
@LocalBean
public class RegisterBean {

    @PersistenceContext
    private EntityManager em;

    public RegisterBean() {

    }

    public String register(User user) {
        Query query = em.createNamedQuery("Role.findByName");
        query.setParameter("name", "USER");
        Role userRole = (Role) query.getSingleResult();

        user.addRole(userRole);

//        em.persist(user);

        return "Registration successful!";
    }
}
