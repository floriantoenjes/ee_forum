package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.ThreadBean;
import com.floriantoenjes.ee.forum.ejb.model.Thread;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ThreadController {

    List<Thread> threads;

    @EJB
    ThreadBean threadBean;


}
