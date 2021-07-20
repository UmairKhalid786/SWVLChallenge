package com.techlad.swvlchallenge.network

import java.io.IOException


/**
 * Created by Umair on 18,July,2021
 */

class NoConnectivityException : IOException() {

    override val message: String
        get() = "No internet connection available, please check your connected WiFi or Data"
}