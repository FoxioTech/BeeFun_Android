package tech.foxio.beefun.ui.screens.auth.authOptions.state

import tech.foxio.beefun.domain.models.LoginModel

data class AuthOptionsState(
    val isLoading: Boolean = false,
    val success: Int = -1,
    val info: LoginModel.Msg.Info? = null,
    val error: String = "",
    val internet:  Boolean = false
)