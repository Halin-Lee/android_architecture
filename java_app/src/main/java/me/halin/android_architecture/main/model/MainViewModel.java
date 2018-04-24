package me.halin.android_architecture.main.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import java.util.List;

import me.halin.android_architecture.model.VersionModel;
import me.halin.android_architecture.model.VersionRepository;
import me.halin.android_architecture.uikit.LiveEvent;

public class MainViewModel extends ViewModel {
    public LiveData<VersionModel> androidVersionModel;
    public LiveData<List<VersionModel>> versionModel;

    public LiveEvent<Void> openOtherEvent = new LiveEvent<>();

    public MainViewModel() {
        this.androidVersionModel = VersionRepository.getInstance().getAndroidVersionModel();
        this.versionModel = VersionRepository.getInstance().getAllVersionModel();
    }

    public void openOther() {
        openOtherEvent.call();
    }

}
