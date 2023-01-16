package mx.com.xtreme.jetpackcomposeinstagram.login.data.repository

import mx.com.xtreme.jetpackcomposeinstagram.login.data.network.client.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: LoginService) {


    suspend fun doLogin(email: String, password: String): Boolean{
        return api.doLogin(email, password)
    }
}