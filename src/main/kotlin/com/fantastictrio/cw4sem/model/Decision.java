package com.fantastictrio.cw4sem.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity(name = "decision")
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String strategyList;

    private int natureStatesCounter;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "created_date")
    private Instant createdDate;

    @Override
    public String toString() {
        return "Decision{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", strategyList='" + strategyList + '\'' +
                ", natureStatesCounter=" + natureStatesCounter +
                ", createdDate=" + createdDate +
                '}';
    }
}
