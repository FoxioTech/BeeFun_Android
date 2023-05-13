package tech.foxio.beefun.data.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import tech.foxio.beefun.domain.models.LoginModel

interface ApiService {

    @FormUrlEncoded
    @POST("api.php?act=user_logon&app=10005")
    suspend fun login(
        @Field("account") username: String,
        @Field("password") password: String
    ): LoginModel
}