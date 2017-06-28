package com.floriantoenjes.ee.forum.ejb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "FORUM", name = "POST")
public class Post {

    @Id
    @SequenceGenerator(
            name = "POST_ID_GENERATOR",
            sequenceName = "SEQ_POST",
            schema = "FORUM",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "POST_ID_GENERATOR"
    )
    private Long id;

    private String text;

    @ManyToOne
    private Thread thread;

    @ManyToOne
    private User author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Post() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
