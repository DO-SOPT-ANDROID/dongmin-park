package org.sopt.dosopttemplate.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import org.sopt.dosopttemplate.BuildConfig
import org.sopt.dosopttemplate.di.qualifier.Auth
import org.sopt.dosopttemplate.di.qualifier.User
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() ->
                    Timber.d(JSONObject(message).toString(4))

                message.isJsonArray() ->
                    Timber.d(JSONObject(message).toString(4))

                else -> {
                    Timber.d("CONNECTION INFO -> $message")
                }
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    @Auth
    fun provideAuthRetrofit(
        jsonConverter: Converter.Factory,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(jsonConverter).client(client).build()
    }

    @Provides
    @Singleton
    @User
    fun provideUserRetrofit(
        jsonConverter: Converter.Factory,
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.USER_BASE_URL)
            .addConverterFactory(jsonConverter).client(client).build()
    }

    @Provides
    @Singleton
    fun provideJsonConverterFactory(): Converter.Factory {
        return Json.asConverterFactory("application/json".toMediaType())
    }
}

fun String?.isJsonObject(): Boolean = this?.startsWith("{") == true && this.endsWith("}")
fun String?.isJsonArray(): Boolean = this?.startsWith("[") == true && this.endsWith("]")
