package com.techlad.swvlchallenge.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter


/**
 * Created by Umair on 18,July,2021
 */

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.loadImage(url)
    }
}

@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}