package com.floriantoenjes.ee.forum.ejb.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "FORUM", name = "ROLE")
public class Role implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "ROLE_ID_GENERATOR",
            sequenceName = "SEQ_ROLE",
            schema = "FORUM",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ROLE_ID_GENERATOR"
    )
    private Long id;

    private String name;

    public Role() {}

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
