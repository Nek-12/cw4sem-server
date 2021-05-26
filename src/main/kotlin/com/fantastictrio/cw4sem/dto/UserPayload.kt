package com.fantastictrio.cw4sem.dto

import com.fantastictrio.cw4sem.model.User
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class UserPayload(
     @field:Pattern(regexp = "[\\w-.]{3,31}")
     val username: String,
     @field:Pattern(regexp = "[\\x21-\\x7E]{8,64}|^\$")
     val password: String?,
     @field:Email
     @field:Size(max = 63, min = 5)
     val email: String,
     @field:Pattern(regexp = "([A-ZА-Я][a-zа-я]{1,30})")
     val firstName: String,
     @field:Pattern(regexp = "([A-ZА-Я][a-zа-я]{1,30})")
     val lastName: String,
     val organizationId: Int? = null,
) {
     constructor(u: User) : this(
          u.username,
          u.password,
          u.email,
          u.firstName,
          u.lastName,
          u.organization?.id
     )
}
