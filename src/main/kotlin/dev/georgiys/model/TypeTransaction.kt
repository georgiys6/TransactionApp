package dev.georgiys.model

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Introspected
@Serdeable
@Entity
@Table(name = "type_transaction")
data class TypeTransaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_transaction_id")
    val typeTransactionId: Int,

    @Max(32)
    @NotBlank
    @Column(name = "type_transaction_label")
    var typeTransactionLabel: String,
)
