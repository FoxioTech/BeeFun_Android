package tech.foxio.beefun.domain.models

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("msg")
    val msg: Msg,
    @SerializedName("time")
    val time: Int
) {
    data class Msg(
        @SerializedName("token")
        val token: String,
        @SerializedName("info")
        val info: Info
    ) {
        data class Info(
            @SerializedName("id")
            val id: Int,
            @SerializedName("pic")
            val pic: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("vip")
            val vip: Long,
            @SerializedName("fen")
            val fen: Int
        )
    }
}