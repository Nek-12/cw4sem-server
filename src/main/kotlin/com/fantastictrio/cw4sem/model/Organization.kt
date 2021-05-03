package com.fantastictrio.cw4sem.model

import com.fantastictrio.cw4sem.dto.OrganizationPayload
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Nationalized
import javax.persistence.*

@JsonIgnoreProperties(value = ["users"])
@Entity(name = "organization")
data class Organization(

    @Nationalized
    @Column(nullable = false, unique = true)
    val name: String,

    @Nationalized
    @Column(nullable = false)
    val type: String,

    @OneToMany(mappedBy = "organization", cascade = [CascadeType.PERSIST])
    val users: List<User> = emptyList(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
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
}
