package com.floriantoenjes.ee.forum.ejb;

import com.floriantoenjes.ee.forum.ejb.model.Post;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class PostBean {

    @PersistenceContext
    private EntityManager em;

    public List<Post> findByThreadId(Long threadId) {
        TypedQuery<Post> query = em.createQuery("SELECT p FROM Post p WHERE p.thread.id = :threadId", Post.class);
        query.setParameter("threadId", threadId);

        return query.getResultList();
    }
}
