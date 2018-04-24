package me.halin.android_architecture;

public class Application extends android.app.Application {

    private static Application application;


    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Application.application = this;
    }
}
