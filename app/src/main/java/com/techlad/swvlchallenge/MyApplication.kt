package com.techlad.swvlchallenge

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.techlad.swvlchallenge.utils.Utils.startNetworkCallback
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates


@HiltAndroidApp
class MyApplication : Application() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        super.onCreate()
        startNetworkCallback(applicationContext)
    }

    object Variables {
        var isNetworkConnected: Boolean by Delegates.observable(false) { _, _, _ ->
        }
    }
}