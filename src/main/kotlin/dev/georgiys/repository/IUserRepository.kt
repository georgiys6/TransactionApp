package dev.georgiys.repository

import dev.georgiys.model.User
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository


@Repository
interface IUserRepository : CrudRepository<User, Int>{

    override fun findAll(): List<User>

    @Query("FROM User where login = :login and password = :pass")
    fun findAllUserTransaction(login: String, pass: String): User?
}