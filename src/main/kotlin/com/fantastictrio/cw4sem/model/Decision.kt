package com.fantastictrio.cw4sem.model

import com.fantastictrio.cw4sem.dto.DecisionPayload
import org.hibernate.annotations.Nationalized
import java.time.Instant
import javax.persistence.*

const val DECISION_SEPARATOR = ";"

@Entity(name = "decision")
data class Decision(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Nationalized
    @Column(nullable = false, unique = true)
    val name: String,

    @Nationalized
    @Column(nullable = false)
    val description: String,

    @Nationalized
    @ElementCollection
    @Column(nullable = false, name = "strategy_list")
    val strategyList: List<String>,

    @Column(nullable = false)
    val natureStatesCounter: Int = 0,

    @ManyToOne
    @JoinColumn(name = "organization_id")
    val organization: Organization? = null,

    @Column(name = "created_date")
    val createdDate: Instant
) {
    constructor(payload: DecisionPayload, id: Int, org: Organization?) :
            this(
                id,
                payload.name,
                payload.description,
                payload.strategyList,
                payload.natureStatesCounter,
                org,
                payload.createdDate
            )
}
