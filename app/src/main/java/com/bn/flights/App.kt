package com.bn.flights

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App: Application() {
    @Inject
    lateinit var tree: Timber.DebugTree

    override fun onCreate() {
        super.onCreate()
        Timber.plant(tree)
    }
}