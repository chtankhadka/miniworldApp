package com.chtan.miniworld

import androidx.compose.ui.window.ComposeUIViewController
import com.chtan.miniworld.di.initKoin
import com.chtan.miniworld.di.initKoinIOS
import com.chtan.miniworld.utils.OrientationManagerProvider
import platform.UIKit.UIInterfaceOrientationMaskLandscape
import platform.UIKit.supportedInterfaceOrientations

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoinIOS()
    }
) { App() }.apply {
    OrientationManagerProvider.instance.lockToLandscape()
    supportedInterfaceOrientations()

}
