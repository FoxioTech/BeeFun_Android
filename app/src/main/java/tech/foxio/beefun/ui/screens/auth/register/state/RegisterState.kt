package tech.foxio.beefun.ui.screens.auth.register.state

data class RegisterState (
    val isLoading : Boolean = false,
    val success : Int = -1,
    val error : String = "",
    val internet :  Boolean = false
)