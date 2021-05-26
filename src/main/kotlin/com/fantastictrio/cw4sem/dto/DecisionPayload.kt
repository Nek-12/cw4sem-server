package com.fantastictrio.cw4sem.dto

import com.fantastictrio.cw4sem.model.Decision
import java.time.Instant
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class DecisionPayload(
    @field:Pattern(regexp = "[a-zA-Zа-яА-Я\\s\\d]{3,63}") //Русский/англ язык + пробел + цифры
    val name: String,

    @field:Size(max = 256)
    val description: String,

    @field:Size(max = 100, min = 1)
    val strategyList: List<String>,

    @field:Min(1)
    val natureStatesCount: Int,


    /**
     * User-defined value needed for the algorithms
     */
    @field:Max(1)
    @field:Min(0)
    val pessimismCoefficient: Double,

    val userId: Int,

    val createdDate: Instant,
) {
    constructor(d: Decision): this(
        d.name,
        d.description,
        d.strategyList,
        d.natureStatesCount,
        d.pessimismCoefficient,
        d.user.id,
        d.createdDate
    )
}
