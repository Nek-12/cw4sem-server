package com.fantastictrio.cw4sem.dto

import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

data class UserPayload(
     @Pattern(regexp = "[\\w-.]{3,31}")
     val username: String,
     @Pattern(regexp = "[\\x21-\\x7E]{8,64}")
     val password: String?,
     @Email
     val email: String,
     @Pattern(regexp = "([A-ZА-Я][a-zа-я]{1,30})")
     val firstName: String,
     @Pattern(regexp = "([A-ZА-Я][a-zа-я]{1,30})")
     val lastName: String,
     val organizationId: Int? = null,
)
