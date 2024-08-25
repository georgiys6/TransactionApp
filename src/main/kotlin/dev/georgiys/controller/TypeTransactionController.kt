package dev.georgiys.controller

import dev.georgiys.model.TypeTransaction
import dev.georgiys.service.TypeTransactionService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/api/typeTransaction")
class TypeTransactionController(private val typeTransactionService: TypeTransactionService) {

    @Post
    fun createTypeTransaction(@Body typeTransaction: TypeTransaction): HttpResponse<TypeTransaction> {
        return HttpResponse.created(typeTransactionService.createTypeTransaction(typeTransaction))
    }

    @Get
    fun getAllTypeTransactions(): HttpResponse<List<TypeTransaction>> {
        return HttpResponse.created(typeTransactionService.allTypeTransactions())
    }

    @Get("/{id}")
    fun getTypeTransactionById(@PathVariable id: Int): HttpResponse<TypeTransaction> {
        return HttpResponse.created(typeTransactionService.getTypeTransaction(id))
    }

    @Put("/{id}")
    fun updateTypeTransactionById(@PathVariable id: Int, @Body typeTransaction: TypeTransaction): HttpResponse<TypeTransaction> {
        return HttpResponse.created(typeTransactionService.updateTypeTransaction(id, typeTransaction))
    }

    @Delete("/{id}")
    fun deleteTypeTransactionById(@PathVariable id: Int): HttpResponse<TypeTransaction> {
        HttpResponse.created(typeTransactionService.deleteTypeTransaction(id))
        return HttpResponse.ok()
    }
}