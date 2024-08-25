package dev.georgiys.model

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import java.sql.Date

@Introspected
@Serdeable
@Entity
@MappedEntity
@Table(name = "user_transaction")
data class UserTransaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    val transactionId: Int,

    @Max(32)
    @NotBlank
    @OneToOne//(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "user_auth_id", nullable = false)
    val userAuth: User,

    @NotBlank
    @Column
    var amount: Double,

    @NotBlank
    @Column(name = "transaction_date")
    var transactionDate: Date,

    @Max(32)
    @NotBlank
    @OneToOne//(cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "type_transaction_id", nullable = false)
    var typeTransaction: TypeTransaction,
)

