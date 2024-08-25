package dev.georgiys.repository

import dev.georgiys.model.User
import dev.georgiys.model.UserTransaction
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.sql.Date

@Repository
interface IUserTransactionRepository: CrudRepository<UserTransaction, Int> {
    override fun findAll(): List<UserTransaction>

    @Query("FROM UserTransaction where userAuth = :user")
    fun findAllUserTransaction(user: User): List<UserTransaction>
}