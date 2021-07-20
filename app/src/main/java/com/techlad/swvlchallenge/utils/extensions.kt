package com.techlad.swvlchallenge.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.techlad.swvlchallenge.R


/**
 * Created by Umair on 16,July,2021
 */

fun View.visibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToParent: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attachToParent)
}

fun Context.showMessage(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(
    url: String,
    @DrawableRes defaultImg: Int = R.drawable.progress_animation,
    @DrawableRes errorImg: Int = R.mipmap.error_placeholder) {

    Glide.with(this)
        .load(url)
        .placeholder(defaultImg) // any placeholder to load at start
        .error(errorImg)  // any image in case of error
        .centerCrop()
        .into(this)
}