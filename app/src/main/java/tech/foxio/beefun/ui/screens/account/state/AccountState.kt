package tech.foxio.beefun.ui.screens.account.state

data class AccountState (
    val isLoading : Boolean = false,
    val success : Int = -1,
    val error : String = "",
    val internet :  Boolean = false
)