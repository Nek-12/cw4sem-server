package com.fantastictrio.cw4sem.dto

data class UserPayload(
     val username: String,
     val password: String?,
     val email: String,
     val firstName: String,
     val lastName: String,
     val organizationId: Int? = null,
)
