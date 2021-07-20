package com.techlad.swvlchallenge.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import com.techlad.swvlchallenge.MyApplication
import com.techlad.swvlchallenge.data.responses.FlickerResponse
import com.techlad.swvlchallenge.utils.Constants.IMAGE_URL


/**
 * Created by Umair on 17,July,2021
 */
object Utils {

    fun replaceAll(photo: FlickerResponse.Photos.Photo): String {
        //"http://farm{farm}.static.flickr.com/{server}/{id}_{secret}.jpg"
        var url = IMAGE_URL
        url = url.replace("{farm}", photo.farm)
        url = url.replace("{server}", photo.server)
        url = url.replace("{id}", photo.id)
        url = url.replace("{secret}", photo.secret)
        return url
    }


    fun calculateCountSize(page: Int): Int {
        return page * Constants.PAGE_SIZE
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun startNetworkCallback(context: Context) {
        val cm: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    MyApplication.Variables.isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    MyApplication.Variables.isNetworkConnected = false
                }
            })
    }
}