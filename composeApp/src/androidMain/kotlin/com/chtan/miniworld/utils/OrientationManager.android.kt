package com.chtan.miniworld.utils

import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity

actual class OrientationManager {
    private var activity: ComponentActivity? = null

    fun setActivity(activity: ComponentActivity) {
        this.activity = activity
    }

    actual fun lockToLandscape() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}

// Singleton access
object OrientationManagerProvider {
    val instance: OrientationManager = OrientationManager()
}