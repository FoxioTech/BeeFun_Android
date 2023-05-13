package tech.foxio.beefun.ui.screens.auth.authOptions.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tech.foxio.beefun.data.repository.UserRepository
import tech.foxio.beefun.ui.screens.auth.authOptions.state.AuthOptionsState
import javax.inject.Inject

@HiltViewModel
class AuthOptionsViewModel @Inject constructor(
) : ViewModel() {
    private val userRepository = UserRepository()
    private val _state = mutableStateOf(AuthOptionsState())
    val state: State<AuthOptionsState> = _state
    private var job: Job? = null

    fun login(username: String, password: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
        }
    }
}