package tech.foxio.beefun.ui.screens.auth.guide.state

data class GuideState (
    val isLoading : Boolean = false,
    val success : Int = -1,
    val error : String = "",
    val internet :  Boolean = false
)