package com.floriantoenjes.ee.forum.ejb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "FORUM", name = "THREAD")
public class Thread {

    @Id
    @SequenceGenerator(
            name = "THREAD_ID_GENERATOR",
            sequenceName = "SEQ_THREAD",
            schema = "FORUM",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "THREAD_ID_GENERATOR"
    )
    private Long id;

    private String name;

    @ManyToOne
    private Board board;

    @ManyToOne
    private User author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Thread() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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
