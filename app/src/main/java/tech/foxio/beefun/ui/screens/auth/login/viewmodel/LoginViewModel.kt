package tech.foxio.beefun.ui.screens.auth.login.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tech.foxio.beefun.data.repository.UserRepository
import tech.foxio.beefun.ui.screens.auth.login.state.LoginState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ViewModel() {
    private val userRepository = UserRepository()
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state
    private var job: Job? = null

    fun login(username: String, password: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
        }
    }
}