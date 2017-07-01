package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.BoardBean;
import com.floriantoenjes.ee.forum.ejb.PostBean;
import com.floriantoenjes.ee.forum.ejb.ThreadBean;
import com.floriantoenjes.ee.forum.ejb.model.Board;
import com.floriantoenjes.ee.forum.ejb.model.Post;
import com.floriantoenjes.ee.forum.ejb.model.Thread;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class ThreadController {

    private Long threadId;

    private Long boardId;

    private Board board;

    private List<Thread> threads;

    @Inject
    private Post post;

    @Inject
    private Thread thread;

    @EJB
    private BoardBean boardBean;

    @EJB
    private ThreadBean threadBean;

    @EJB
    private PostBean postBean;

    public void initThread() {
        thread = threadBean.find(threadId);
    }

    public String createThread(User user) {

        thread.setAuthor(user);
        thread.setBoard(getBoard());
        thread.setCreated(new Date());

        thread = threadBean.createThread(thread);

        post.setAuthor(user);
        post.setThread(thread);
        post.setCreated(new Date());

        postBean.createPost(post);

        return "pretty:viewBoard";
    }

    public String editThread() {
        String name = thread.getName();
        thread = threadBean.find(threadId);
        thread.setName(name);

        threadBean.editThread(thread);

        return "pretty:viewBoard";
    }

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public List<Thread> getThreads() {
        threads = threadBean.findByBoardId(boardId);
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Board getBoard() {
        return boardBean.find(boardId);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }
}
