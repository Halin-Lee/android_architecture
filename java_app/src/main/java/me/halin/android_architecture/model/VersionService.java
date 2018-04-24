package me.halin.android_architecture.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VersionService {

    @GET("/appres/{platform}/versioninfo.json")
    Call<VersionModel> getVersionInfo(@Path("platform") String platform);

}
