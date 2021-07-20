package com.techlad.swvlchallenge.ui.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


/**
 * Created by Umair on 18,July,2021
 */
abstract class BaseActivity<B : ViewBinding>(val bindingFactory: (LayoutInflater) -> B) :
    AppCompatActivity() {

    protected val binding: B by lazy { bindingFactory(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        changeStatusBar()
        setContentView(binding.root)
        supportActionBar?.hide()
        onPostInit()
    }

    abstract fun onPostInit()


    private fun changeStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = Color.WHITE
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}