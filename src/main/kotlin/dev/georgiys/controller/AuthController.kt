package dev.georgiys.controller

import dev.georgiys.model.User
import dev.georgiys.service.AuthService
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*

@Controller("/api/auth")
class AuthController(
    private val authService: AuthService,
) {

    @Post
    fun authUser(@Body credentials: Map<String, String>): HttpResponse<User> {
        val login = credentials["login"] ?: ""
        val pass = credentials["pass"] ?: ""
        val req = HttpResponse.ok(authService.authUser(login, pass))
        return if (req.status == HttpStatus.OK) {
            HttpResponse.accepted()
        } else {
            HttpResponse.notFound()
        }
    }

    @Get
    fun getAllUsers(): HttpResponse<List<User>> {
        return HttpResponse.created(authService.allUsers())
    }

    @Get("/{id}")
    fun getUserById(@PathVariable id: Int): HttpResponse<User> {
        return HttpResponse.created(authService.getUser(id))
    }

    @Put("/{id}")
    fun updateUserById(@PathVariable id: Int, @Body user: User): HttpResponse<User> {
        return HttpResponse.created(authService.updateUser(id, user))
    }

    @Delete("/{id}")
    fun deleteUserById(@PathVariable id: Int): HttpResponse<User> {
        HttpResponse.created(authService.deleteUser(id))
        return HttpResponse.ok()
    }
}