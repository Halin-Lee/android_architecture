package me.halin.android_architecture.other;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.halin.android_architecture.Application;
import me.halin.android_architecture.R;
import me.halin.android_architecture.model.VersionModel;
import me.halin.android_architecture.model.VersionModelDao;
import me.halin.android_architecture.model.VersionModelDatabase;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        new Thread(new Runnable() {
            @Override
            public void run() {
                VersionModelDao dao = Room.databaseBuilder(Application.getApplication(), VersionModelDatabase.class, "version.db").build().versionModelDao();
                dao.save(new VersionModel(0, 0, "android", System.currentTimeMillis()));
            }
        }).start();
    }
}
