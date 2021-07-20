package com.techlad.swvlchallenge.network

import com.techlad.swvlchallenge.MyApplication.Variables.isNetworkConnected
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by Umair on 18,July,2021
 *
 * This class will deal with network responses
 */

class NoInternetInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isNetworkConnected) {
            throw NoConnectivityException()
        } else {
            chain.proceed(chain.request())
        }
    }
}