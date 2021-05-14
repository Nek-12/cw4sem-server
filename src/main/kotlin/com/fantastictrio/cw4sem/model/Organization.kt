package com.fantastictrio.cw4sem.model

import com.fantastictrio.cw4sem.dto.OrganizationPayload
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Nationalized
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.Pattern

@JsonIgnoreProperties(value = ["users"])
@Entity(name = "organization")
data class Organization(

    @Nationalized
    @Pattern(regexp = "[a-zA-Zа-яА-Я\\s\\d]{3,30}")
    @Column(nullable = false, unique = true)
    val name: String,

    @Nationalized
    @Pattern(regexp = "[a-zA-Zа-яА-Я\\s]{3,30}")
    @Column(nullable = false)
    val type: String,

    @OneToMany(mappedBy = "organization", cascade = [CascadeType.PERSIST])
    val users: List<User> = emptyList(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false)
    val createdDate: Instant = Instant.now(),
) {
    constructor(payload: OrganizationPayload, id: Int = 0, users: List<User> = emptyList()) : this(
        payload.name,
        payload.type,
        users,
        id,
    )

    fun toPayload(): OrganizationPayload {
        return OrganizationPayload(this)
    }

    val employeeCount: Int
    get() = users.size
}
