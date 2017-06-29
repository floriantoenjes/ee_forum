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

    private Long boardId;

    private List<Thread> threads;

    @EJB
    private ThreadBean threadBean;

    public void init() {
        if (boardId != null) {
            threads = threadBean.findByBoardId(boardId);
        }
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }
}
