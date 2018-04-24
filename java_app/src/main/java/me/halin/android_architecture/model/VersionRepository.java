package me.halin.android_architecture.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;

import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import me.halin.android_architecture.Application;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VersionRepository {

    public static final String TAG = VersionRepository.class.getName();

    private static final VersionRepository instance = new VersionRepository();

    public static VersionRepository getInstance() {
        return instance;
    }

    private VersionService webService = new Retrofit.Builder()
            .baseUrl("https://www.17track.net")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
            .create(VersionService.class);
    private VersionModelDatabase database = Room.databaseBuilder(Application.getApplication(), VersionModelDatabase.class, "version.db").build();


    public LiveData<VersionModel> getAndroidVersionModel() {
//        updateVersionModel("android");
        return database.versionModelDao().load("android");
    }


    public LiveData<List<VersionModel>> getAllVersionModel() {
        updateVersionModel("android");
        updateVersionModel("ios");
        return database.versionModelDao().loadAll();
    }

    private void updateVersionModel(final String platform) {

        webService.getVersionInfo(platform).enqueue(new Callback<VersionModel>() {
            @Override
            public void onResponse(Call<VersionModel> call, Response<VersionModel> response) {
                VersionModel versionModel = response.body();
                versionModel.setPlatform(platform);
                versionModel.setUpdateTime(System.currentTimeMillis());
                database.versionModelDao().save(versionModel);

            }

            @Override
            public void onFailure(Call<VersionModel> call, Throwable t) {

            }
        });
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final X509TrustManager[] trustAllCerts = new X509TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


            return new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
