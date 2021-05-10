package com.fantastictrio.cw4sem.model

import com.fantastictrio.cw4sem.dto.DecisionPayload
import org.hibernate.annotations.Nationalized
import java.time.Instant
import javax.persistence.*

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

    @Column(name = "created_date")
    val createdDate: Instant = Instant.now(),

    @ManyToOne
    @JoinColumn(name = "organization_id")
    val organization: Organization,


    @OneToMany(mappedBy = "decision", cascade = [CascadeType.ALL])
    val records: List<DecisionRecord> = emptyList(),

    /**
     * Represents the amount of states the nature can possibly be in.
     */
    @Column(nullable = false)
    val natureStatesCount: Int = 0,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
) {
    constructor(payload: DecisionPayload, org: Organization, id: Int = 0) :
            this(
                payload.name,
                payload.description,
                payload.strategyList,
                payload.createdDate,
                org,
                emptyList(), //always invalidate all records
                payload.natureStatesCount,
                id,
            )
    fun toPayload(): DecisionPayload {
        return DecisionPayload(this)
    }
}
