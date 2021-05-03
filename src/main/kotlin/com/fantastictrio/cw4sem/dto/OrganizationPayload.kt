package com.fantastictrio.cw4sem.dto

import com.fantastictrio.cw4sem.model.Organization

data class OrganizationPayload(
    val name: String,
    val type: String,
) {
    constructor(org: Organization): this(org.name,org.type)
}
