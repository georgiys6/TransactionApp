package dev.georgiys.controller

import dev.georgiys.model.UserTransaction
import dev.georgiys.service.AuthService
import dev.georgiys.service.UserTransactionService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/api/userTransaction")
class UserTransactionController(
    private val userTransactionService: UserTransactionService,
    private val authService: AuthService,
) {
    @Post
    fun createUserTransaction(@Body userTransaction: UserTransaction): HttpResponse<UserTransaction> {
        return HttpResponse.created(userTransactionService.createUserTransaction(userTransaction))
    }

    @Get
    fun getAllUserTransactions(): HttpResponse<List<UserTransaction>> {
        return HttpResponse.created(userTransactionService.allUserTransactions())
    }

    @Get("/{id}")
    fun getUserTransactionById(@PathVariable id: Int): HttpResponse<List<UserTransaction>> {
        val user = authService.getUser(id)
        return HttpResponse.created(userTransactionService.getAllUserTransactions(user))
    }

    @Get("/{userAuthId}/{page}")
    fun getUserTransactionPage(@PathVariable userAuthId: Int, @PathVariable page: Int): HttpResponse<UserTransaction> {
        return try {
            HttpResponse.created(userTransactionService.getUserTransactionPage(userAuthId, page - 1))
        } catch (e: IndexOutOfBoundsException) {
            HttpResponse.notFound()
        }
    }

    @Put("/{id}")
    fun updateUserTransactionById(@PathVariable id: Int, @Body userTransaction: UserTransaction)
    : HttpResponse<UserTransaction> {
        return HttpResponse.created(userTransactionService.updateUserTransaction(id, userTransaction))
    }

    @Delete("/{id}")
    fun deleteUserTransactionById(@PathVariable id: Int): HttpResponse<UserTransaction> {
        HttpResponse.created(userTransactionService.deleteUserTransaction(id))
        return HttpResponse.ok()
    }
}