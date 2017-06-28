package com.floriantoenjes.ee.forum.ejb.model;

import javax.persistence.*;

@Entity
@Table(schema = "FORUM", name = "BOARD")
public class Board {

    @Id
    @SequenceGenerator(
            name = "BOARD_ID_GENERATOR",
            sequenceName = "SEQ_BOARD",
            schema = "FORUM",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BOARD_ID_GENERATOR"
    )
    private Long id;

    private String name;

    public Board() {}

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
}
