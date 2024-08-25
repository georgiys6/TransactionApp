package dev.georgiys.repository

import dev.georgiys.model.TypeTransaction
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface ITypeTransactionRepository : CrudRepository<TypeTransaction, Int> {
    override fun findAll(): List<TypeTransaction>
}