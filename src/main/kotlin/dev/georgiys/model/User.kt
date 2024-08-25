package dev.georgiys.model

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Introspected
@Serdeable
@Entity
@Table(name = "user_auth")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_auth_id")
    val userAuthId: Int,

    @Max(32)
    @NotBlank
    @Column
    var login: String,

    @Max(32)
    @NotBlank
    @Column(name = "pass")
    var password: String
)