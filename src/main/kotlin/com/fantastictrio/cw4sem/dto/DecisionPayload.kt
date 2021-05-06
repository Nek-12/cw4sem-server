package com.fantastictrio.cw4sem.dto

import com.fantastictrio.cw4sem.model.Decision
import java.time.Instant

data class DecisionPayload(
    val name: String,
    val description: String,
    val strategyList: List<String>,
    val natureStatesCounter: Int,
    val organizationId: Int?,
    val createdDate: Instant,
) {
    constructor(d: Decision): this(d.name,d.description,d.strategyList,d.natureStatesCount,d.organization?.id,d
        .createdDate)
}
