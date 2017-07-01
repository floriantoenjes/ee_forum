package com.floriantoenjes.ee.forum.ejb;

import com.floriantoenjes.ee.forum.ejb.model.Thread;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class ThreadBean {

    @PersistenceContext
    private EntityManager em;

    public ThreadBean() {
    }

    public Thread find(Long id) {
        return em.find(Thread.class, id);
    }

    public List<Thread> findByBoardId(Long boardId) {
        TypedQuery<Thread> query = em.createQuery("SELECT t FROM Thread t WHERE t.board.id = :boardId", Thread.class);
        query.setParameter("boardId", boardId);

        return query.getResultList();
    }

    public Thread createThread(Thread thread) {
        em.persist(thread);
        return thread;
    }
}
