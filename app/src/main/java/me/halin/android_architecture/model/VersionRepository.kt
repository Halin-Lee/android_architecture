package me.halin.android_architecture.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import me.halin.android_architecture.Application
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager


object VersionRepository {
    private val webService = Retrofit.Builder()
            .baseUrl("https://www.17track.net")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
            .create(VersionService::class.java)!!

    private val database = Room.databaseBuilder(Application.application, VersionModelDatabase::class.java, "version.db").build()


    fun getAndroidVersionModel(): LiveData<VersionModel> {
//        updateVersionModel()
        return database.versionModelDao().load("android")
    }

    fun getAllVersionModel(): LiveData<List<VersionModel>> {
        updateVersionModel("android")
        updateVersionModel("ios")
        return database.versionModelDao().loadAll()
    }

    private fun updateVersionModel(platform: String) {

        webService.getVersionInfo(platform).enqueue(object : Callback<VersionModel> {
            override fun onFailure(call: Call<VersionModel>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<VersionModel>?, response: Response<VersionModel>?) {
                val versionModel = response?.body()!!
                versionModel.platform = platform
                versionModel.updateTime = System.currentTimeMillis()
                database.versionModelDao().save(versionModel)
            }
        })
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient {
        val trustAllCerts = arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory = sslContext.socketFactory
        return OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory)
                .hostnameVerifier { _, _ -> true; }
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .build()
    }


}

