package com.fantastictrio.cw4sem.model
import com.fantastictrio.cw4sem.dto.OrganizationPayload
import javax.persistence.*


@Entity(name = "organization")
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable = false)
    val type: String,

    @OneToMany(mappedBy = "organization", cascade = [CascadeType.PERSIST])
    val users: List<User> = emptyList(),
) {
    constructor(payload: OrganizationPayload, id: Int, users: List<User> = emptyList()): this(id,payload.name,payload.type)
}
