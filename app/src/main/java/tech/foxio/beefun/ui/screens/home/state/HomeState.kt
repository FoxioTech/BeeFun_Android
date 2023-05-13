package tech.foxio.beefun.ui.screens.home.state

data class HomeState (
    val isLoading : Boolean = false,
    val success : Int = -1,
    val error : String = "",
    val internet :  Boolean = false
)