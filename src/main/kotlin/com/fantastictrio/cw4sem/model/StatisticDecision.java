package com.fantastictrio.cw4sem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@Entity(name = "statistic_decision")
@RequiredArgsConstructor
public class StatisticDecision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nationalized
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Nationalized
    private String data;
}
