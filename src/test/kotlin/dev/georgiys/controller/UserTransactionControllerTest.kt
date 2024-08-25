package dev.georgiys.controller

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@MicronautTest
class UserTransactionControllerTest {

    @Inject
    private lateinit var userTransactionController: UserTransactionController

    @Test
    fun `check user page transaction`() {
        userTransactionController.getUserTransactionPage(1, 1)
    }
}