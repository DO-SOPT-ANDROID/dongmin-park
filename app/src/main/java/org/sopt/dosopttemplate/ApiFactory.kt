package org.sopt.dosopttemplate

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosopttemplate.ApiFactory.BASE_URL
import org.sopt.dosopttemplate.ApiFactory.USER_BASE_URL
import retrofit2.Retrofit

object ApiFactory {
    const val BASE_URL = BuildConfig.AUTH_BASE_URL
    const val USER_BASE_URL = BuildConfig.USER_BASE_URL

    private fun getLogOkHttpClient(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("Retrofit2", "CONNECTION INFO -> $message")
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(getLogOkHttpClient())
        .build()

    fun getRetrofit(url: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

//    val retrofit: Retrofit by lazy{
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
//            .build()
//    }

    inline fun <reified T, B> create(url: B): T = getRetrofit(url.toString()).create<T>(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService, String>(BASE_URL)
    val userService = ApiFactory.create<UserService, String>(USER_BASE_URL)
}
