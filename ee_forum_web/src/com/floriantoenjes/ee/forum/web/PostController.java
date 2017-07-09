package com.floriantoenjes.ee.forum.web;

import com.floriantoenjes.ee.forum.ejb.PostBean;
import com.floriantoenjes.ee.forum.ejb.ThreadBean;
import com.floriantoenjes.ee.forum.ejb.model.Post;
import com.floriantoenjes.ee.forum.ejb.model.Thread;
import com.floriantoenjes.ee.forum.ejb.model.User;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class PostController implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public String loadPosts() {
        thread = threadBean.find(threadId);
        if (thread == null) {
            return "pretty:not-found";
        }
        posts = postBean.findByThreadId(threadId);

        return null;
    }

    public String createPost(User author) {
        post.setThread(threadBean.find(threadId));
        post.setAuthor(author);
        post.setCreated(new Date());

        postBean.createPost(post);

        return "pretty:viewThread";
    }

    public String editPost() {
        postBean.editPost(post);

        return "pretty:viewThread";
    }

    public String deletePost() {
        postBean.deletePost(post);

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
        return threadBean.find(threadId);
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