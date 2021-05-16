package com.fantastictrio.cw4sem.dto

import com.fantastictrio.cw4sem.model.Organization
import javax.validation.constraints.Pattern

data class OrganizationPayload(
    @field:Pattern(regexp = "[a-zA-Zа-яА-Я\\s\\d]{2,30}")
    val name: String,
    @field:Pattern(regexp = "[a-zA-Zа-яА-Я\\s]{2,30}")
    val type: String,
) {
    constructor(org: Organization): this(org.name,org.type)
}
