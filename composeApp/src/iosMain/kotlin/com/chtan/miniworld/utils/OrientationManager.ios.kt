package com.chtan.miniworld.utils

import platform.Foundation.setValue
import platform.UIKit.*

actual class OrientationManager {
    actual fun lockToLandscape() {
        UIDevice.currentDevice.setValue(UIInterfaceOrientationMaskLandscape, forKey = "orientation")
        UIViewController.attemptRotationToDeviceOrientation()
    }
}

// Singleton
object OrientationManagerProvider {
    val instance: OrientationManager = OrientationManager()
}