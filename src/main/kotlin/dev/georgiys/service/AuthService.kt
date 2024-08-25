package dev.georgiys.service

import dev.georgiys.exception.UserNotFoundException
import dev.georgiys.model.User
import dev.georgiys.repository.IUserRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
open class AuthService(private val userRepository: IUserRepository) {

    @Transactional
    open fun createUser(user: User): User {
        userRepository.save(user)
        return user
    }

    fun authUser(login: String, pass: String): User {
        val user = userRepository.findAllUserTransaction(login, pass)
        if (user != null)
            return user
        else
            throw UserNotFoundException()
    }

    fun allUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUser(id: Int): User {
        return userRepository.findById(id).orElseThrow(::UserNotFoundException)
    }

    @Transactional
    open fun updateUser(id: Int, user: User): User {
        val prevUser = getUser(id)
        prevUser.login = user.login
        prevUser.password = user.password
        return userRepository.save(user)
    }

    fun deleteUser(id: Int){
        userRepository.deleteById(id)
    }
}