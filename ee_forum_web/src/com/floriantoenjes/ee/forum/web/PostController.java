package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.PostBean;
import com.floriantoenjes.ee.forum.ejb.ThreadBean;
import com.floriantoenjes.ee.forum.ejb.model.Post;
import com.floriantoenjes.ee.forum.ejb.model.Thread;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class PostController {

    private Long boardId;
    private Long threadId;
    private Long postId;

    private Thread thread;

    private List<Post> posts;

    @Inject
    private Post post;

    @EJB
    private PostBean postBean;

    @EJB
    private ThreadBean threadBean;

    public void initPost() {
        post = postBean.find(postId);
    }

    public String createPost(User author) {
        post.setThread(threadBean.find(threadId));
        post.setAuthor(author);
        post.setCreated(new Date());

        postBean.createPost(post);

        return "pretty:viewThread";
    }

    public String editPost() {
        String text = post.getText();
        post = postBean.find(postId);
        post.setText(text);
        postBean.editPost(post);

        return "pretty:viewThread";
    }

    public String deletePost() {
        postBean.deletePost(postId);

        return "pretty:viewThread";
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
        posts = postBean.findByThreadId(threadId);
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

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

}