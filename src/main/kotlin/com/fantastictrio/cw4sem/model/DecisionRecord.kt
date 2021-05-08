package com.fantastictrio.cw4sem.model

import javax.persistence.*

/**
 * The different <>Criterion fields represent which one of the strategies should the user choose according
 * to the specified method. If the decision was changed, then the criteria will be INVALIDATED (undefined)
 */
@Entity(name = "decision_record")
data class DecisionRecord(
    @Column(nullable = false)
    val waldCriterion: Int,
    @Column(nullable = false)
    val savageCriterion: Int,
    @Column(nullable = false)
    val gurvitzCriterion: Int,

    @ManyToOne
    @JoinColumn(name = "decision_id")
    val decision: Decision,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
)
