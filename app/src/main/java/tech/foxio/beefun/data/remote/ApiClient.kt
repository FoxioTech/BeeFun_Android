package tech.foxio.beefun.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.foxio.beefun.util.AppConfig.BASE_URL
import tech.foxio.beefun.util.LogUtil.logd
import tech.foxio.beefun.util.LogUtil.loge
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

object ApiClient {

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(LoggingInterceptor())
        .addInterceptor(HeaderInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // 添加请求头
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ptla_lhXzDdOJEfhBj5VpabPbCMVOFENCV63JwRolbALJeZu")
            .addHeader("Content-Type", "application/json")
            .header("Accept", "Application/vnd.pterodactyl.v1+json")
            .method(originalRequest.method(), originalRequest.body())
            .build()

        return chain.proceed(newRequest)
    }
}

class LoggingInterceptor : Interceptor {
    companion object {
        private val UTF8 = Charset.forName("UTF-8")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // 记录请求信息
        val requestStart = System.nanoTime()
        loge("==========Start request==========")
        logd("Sending request: ${request.method()} ${request.url()}")
        logd("Request headers: ${request.headers()}")
        request.body()?.let {
            val buffer = Buffer().apply { it.writeTo(this) }
            logd("Request body: ${buffer.readString(UTF8)}")
        }

        // 执行请求
        val response = chain.proceed(request)

        // 记录响应信息
        val responseTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - requestStart)
        logd("Received response (${responseTime}ms): ${response.code()} ${response.message()}")
        response.body()?.let { responseBody ->
            val responseBodyString = responseBody.string()
            logd("Response body: $responseBodyString")
            loge("==========End request==========")
            val contentType = responseBody.contentType()
            val contentLength = responseBody.contentLength()
            val wrappedBody = ResponseBody.create(contentType, responseBodyString)
            return response.newBuilder()
                .body(wrappedBody)
                .build()
        }
        return response
    }
}