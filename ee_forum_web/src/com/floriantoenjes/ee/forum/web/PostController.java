package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.PostBean;
import com.floriantoenjes.ee.forum.ejb.ThreadBean;
import com.floriantoenjes.ee.forum.ejb.model.Post;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class PostController {
    private Long boardId = 0L;
    private Long threadId = 0L;

    private List<Post> posts;

    @Inject
    private Post post;

    @EJB
    private PostBean postBean;

    @EJB
    private ThreadBean threadBean;

    public void init() {
        if (threadId != null) {
            posts = postBean.findByThreadId(threadId);
        }
    }

    public String createPost(User author) {
        post.setThread(threadBean.find(threadId));
        post.setAuthor(author);
        post.setCreated(new Date());

        postBean.createPost(post);

        return "thread.xhtml?thread_id" + threadId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
