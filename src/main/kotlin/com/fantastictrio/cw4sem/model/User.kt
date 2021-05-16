package com.fantastictrio.cw4sem.model

import com.fantastictrio.cw4sem.dto.UserPayload
import org.hibernate.annotations.Nationalized
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Column(unique = true, nullable = false)
    val email: String,

    @Nationalized
    @Column(name = "first_name", nullable = false)
    val firstName: String,

    @Nationalized
    @Column(name = "last_name", nullable = false)
    val lastName: String,

    @ManyToOne
    @JoinColumn(name = "organization_id")
    val organization: Organization? = null,

    @Enumerated
    var role: Role,

    @Column(name = "created_date")
    val createdDate: Instant,
) {
    interface UserProjection {
        val id: Int?
        val username: String?
    }

    fun toPayload(): UserPayload {
        return UserPayload(this)
    }

    constructor(
        payload: UserPayload,
        newPassword: String,
        role: Role,
        organization: Organization?,
        id: Int = 0,
        created: Instant = Instant.now()
    ) : this(
        id,
        payload.username,
        newPassword,
        payload.email,
        payload.firstName,
        payload.lastName,
        organization,
        role,
        created
    )
}

