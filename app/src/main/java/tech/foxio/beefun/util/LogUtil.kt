package tech.foxio.beefun.util
import android.util.Log

object LogUtil {
    private const val TAG = "BeeFun"

    fun logd(message: String) {
        if(!AppConfig.Debug) return
        Log.d(TAG, "${this.javaClass.simpleName}: $message")
    }

    fun loge(message: String, throwable: Throwable? = null) {
        if(!AppConfig.Debug) return
        Log.e(TAG, "${this.javaClass.simpleName}: $message", throwable)
    }

    fun logi(message: String) {
        if(!AppConfig.Debug) return
        Log.i(TAG, "${this.javaClass.simpleName}: $message")
    }

    fun logv(message: String) {
        if(!AppConfig.Debug) return
        Log.v(TAG, "${this.javaClass.simpleName}: $message")
    }

    fun logw(message: String) {
        if(!AppConfig.Debug) return
        Log.w(TAG, "${this.javaClass.simpleName}: $message")
    }
}