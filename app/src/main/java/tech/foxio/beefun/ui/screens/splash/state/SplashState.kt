package tech.foxio.beefun.ui.screens.splash.state

data class SplashState (
    val isLoading : Boolean = false,
    val success : Int = -1,
    val error : String = "",
    val internet :  Boolean = false
)