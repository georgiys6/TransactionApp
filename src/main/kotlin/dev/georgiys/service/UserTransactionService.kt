package dev.georgiys.service

import dev.georgiys.model.User
import dev.georgiys.model.UserTransaction
import dev.georgiys.repository.IUserRepository
import dev.georgiys.repository.IUserTransactionRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.inject.Singleton

@Singleton
open class UserTransactionService(
    private val userTransactionRepository: IUserTransactionRepository,
    private val userRepository: IUserRepository) {

    @Transactional
    open fun createUserTransaction(userTransaction: UserTransaction): UserTransaction {
        userTransactionRepository.save(userTransaction)
        return userTransaction
    }

    fun allUserTransactions(): List<UserTransaction> {
        return userTransactionRepository.findAll()
    }

    fun getAllUserTransactions(user: User): List<UserTransaction> {
        return userTransactionRepository.findAllUserTransaction(user)
    }

    fun getUserTransaction(id: Int): UserTransaction {
        return userTransactionRepository.findById(id).orElseThrow(::RuntimeException)
    }

    fun getUserTransactionPage(userAuthId: Int, page: Int): UserTransaction {
        val user = userRepository.findById(userAuthId).get()
        val allTransaction = getAllUserTransactions(user)
        return allTransaction[page]
    }

    fun updateUserTransaction(id: Int, userTransaction: UserTransaction): UserTransaction {
        val prevUserTransaction = getUserTransaction(id)
        prevUserTransaction.transactionDate = userTransaction.transactionDate
        prevUserTransaction.typeTransaction = userTransaction.typeTransaction
        prevUserTransaction.amount = userTransaction.amount
        return userTransactionRepository.save(userTransaction)
    }

    fun deleteUserTransaction(id: Int){
        userTransactionRepository.deleteById(id)
    }
}