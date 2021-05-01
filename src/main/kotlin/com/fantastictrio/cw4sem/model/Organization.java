package com.fantastictrio.cw4sem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "organization",cascade=CascadeType.PERSIST)
    private List<User> users;

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", users=" + users +
                '}';
    }
}
