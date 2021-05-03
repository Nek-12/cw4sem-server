package com.fantastictrio.cw4sem.model

import com.fantastictrio.cw4sem.dto.DecisionPayload
import org.hibernate.annotations.Nationalized
import java.time.Instant
import javax.persistence.*

const val DECISION_SEPARATOR = ";"

@Entity(name = "decision")
data class Decision(
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

    @ManyToOne
    @JoinColumn(name = "organization_id")
    val organization: Organization? = null,

    @Column(name = "created_date")
    val createdDate: Instant,

    @Column(nullable = false)
    val natureStatesCounter: Int = 0,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
) {
    constructor(payload: DecisionPayload, org: Organization? = null, id: Int = 0) :
            this(
                payload.name,
                payload.description,
                payload.strategyList,
                org,
                payload.createdDate,
                payload.natureStatesCounter,
                id,
            )
    fun toPayload(): DecisionPayload {
        return DecisionPayload(this)
    }
}
