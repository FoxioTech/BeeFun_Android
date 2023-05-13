package tech.foxio.beefun.ui.screens.auth.login.state

import tech.foxio.beefun.domain.models.LoginModel

data class LoginState(
    val isLoading: Boolean = false,
    val success: Int = -1,
    val info: LoginModel.Msg.Info? = null,
    val error: String = "",
    val internet:  Boolean = false,
)