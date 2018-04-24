package me.halin.android_architecture.main.model

import android.arch.lifecycle.ViewModel
import me.halin.android_architecture.model.VersionRepository
import me.halin.android_architecture.uikit.LiveEvent

public class MainViewModel : ViewModel() {

    public val versionModel = VersionRepository.getAllVersionModel()
    public val androidVersionModel = VersionRepository.getAndroidVersionModel()
    public val openOtherEvent = LiveEvent<Void>()

    public fun openOther() {
        openOtherEvent.call()
    }
}