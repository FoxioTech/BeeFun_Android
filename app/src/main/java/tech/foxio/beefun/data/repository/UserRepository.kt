package tech.foxio.beefun.data.repository

import tech.foxio.beefun.data.remote.ApiClient
import tech.foxio.beefun.domain.models.LoginModel

class UserRepository {
    private val apiService = ApiClient.apiService

    suspend fun login(username: String, password: String) : LoginModel = apiService.login(username, password)
}