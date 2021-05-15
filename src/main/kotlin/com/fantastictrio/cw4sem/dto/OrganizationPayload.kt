package com.fantastictrio.cw4sem.dto

import com.fantastictrio.cw4sem.model.Organization
import javax.validation.constraints.Pattern

data class OrganizationPayload(
    @Pattern(regexp = "[a-zA-Zа-яА-Я\\s\\d]{3,30}")
    val name: String,
    @Pattern(regexp = "[a-zA-Zа-яА-Я\\s]{3,30}")
    val type: String,
) {
    constructor(org: Organization): this(org.name,org.type)
}
