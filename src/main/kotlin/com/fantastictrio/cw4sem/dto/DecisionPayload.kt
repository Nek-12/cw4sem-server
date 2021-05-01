package com.fantastictrio.cw4sem.dto

import java.time.Instant

data class DecisionPayload(
    val name: String,
    val description: String,
    val strategyList: List<String>,
    val natureStatesCounter: Int,
    val organizationId: Int,
    val createdDate: Instant,
)
