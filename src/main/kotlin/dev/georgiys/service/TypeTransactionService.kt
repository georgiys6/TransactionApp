package dev.georgiys.service

import dev.georgiys.model.TypeTransaction
import dev.georgiys.repository.ITypeTransactionRepository
import jakarta.inject.Singleton

@Singleton
class TypeTransactionService(private val typeTransactionRepository: ITypeTransactionRepository) {

    fun createTypeTransaction(typeTransaction: TypeTransaction): TypeTransaction {
        typeTransactionRepository.save(typeTransaction)
        return typeTransaction
    }

    fun allTypeTransactions(): List<TypeTransaction> {
        return typeTransactionRepository.findAll()
    }

    fun getTypeTransaction(id: Int): TypeTransaction {
        return typeTransactionRepository.findById(id).orElseThrow(::RuntimeException)
    }

    fun updateTypeTransaction(id: Int, typeTransaction: TypeTransaction): TypeTransaction {
        val prevTypeTransaction = getTypeTransaction(id)
        prevTypeTransaction.typeTransactionLabel = typeTransaction.typeTransactionLabel
        return typeTransactionRepository.save(typeTransaction)
    }

    fun deleteTypeTransaction(id: Int){
        typeTransactionRepository.deleteById(id)
    }
}