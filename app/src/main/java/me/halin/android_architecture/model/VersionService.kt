package me.halin.android_architecture.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VersionService {

    @GET("/appres/{platform}/versioninfo.json")
    fun getVersionInfo(@Path("platform") platform: String): Call<VersionModel>


}