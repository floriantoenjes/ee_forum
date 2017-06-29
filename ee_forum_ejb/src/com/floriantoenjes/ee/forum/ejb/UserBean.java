package com.floriantoenjes.ee.forum.ejb;

import com.floriantoenjes.ee.forum.ejb.model.Role;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public UserBean() {

    }

    public String register(User user) {
        Query query = em.createNamedQuery("Role.findByName");
        query.setParameter("name", "USER");
        Role userRole = (Role) query.getSingleResult();

        user.addRole(userRole);

//        em.persist(user);

        return "Registration successful!";
    }

    public Optional<User> find(String username, String password) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getResultList().stream().findFirst();
    }
}
