package mx.com.xtreme.jetpackcomposeinstagram.login.domain.usescases

import mx.com.xtreme.jetpackcomposeinstagram.login.data.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String):Boolean{
        return repository.doLogin(email, password)
    }

}