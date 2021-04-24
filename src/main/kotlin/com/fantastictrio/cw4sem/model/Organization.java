package com.fantastictrio.cw4sem.model;

import lombok.Data;

import javax.persistence.*;

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
}
