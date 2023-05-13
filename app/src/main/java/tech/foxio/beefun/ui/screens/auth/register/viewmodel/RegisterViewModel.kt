package tech.foxio.beefun.ui.screens.auth.register.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import tech.foxio.beefun.ui.screens.auth.register.state.RegisterState
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
) : ViewModel() {
    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state
    private var job: Job? = null

    fun login(username: String, password: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
        }
    }
}