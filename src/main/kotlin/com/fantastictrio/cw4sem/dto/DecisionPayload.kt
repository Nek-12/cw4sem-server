package com.fantastictrio.cw4sem.dto

import com.fantastictrio.cw4sem.model.Decision
import java.time.Instant
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class DecisionPayload(
    @Pattern(regexp = "[a-zA-Zа-яА-Я\\s\\d]{3,30}") //Любой язык + пробел + цифры
    val name: String,

    @Size(max = 256)
    val description: String,

    val strategyList: List<String>,

    @Min(1)
    val natureStatesCount: Int,

    val organizationId: Int,

    val createdDate: Instant,
) {
    constructor(d: Decision): this(d.name,d.description,d.strategyList,d.natureStatesCount,d.organization?.id,d
        .createdDate)
}
