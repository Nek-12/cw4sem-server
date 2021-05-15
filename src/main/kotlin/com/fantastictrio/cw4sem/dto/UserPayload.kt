package com.fantastictrio.cw4sem.dto

import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

data class UserPayload(
     @field:Pattern(regexp = "[\\w-.]{3,31}")
     val username: String,
     @field:Pattern(regexp = "[\\x21-\\x7E]{8,64}")
     val password: String?,
     @field:Email
     val email: String,
     @field:Pattern(regexp = "([A-ZА-Я][a-zа-я]{1,30})")
     val firstName: String,
     @field:Pattern(regexp = "([A-ZА-Я][a-zа-я]{1,30})")
     val lastName: String,
     val organizationId: Int? = null,
)
