package com.techlad.swvlchallenge.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.techlad.swvlchallenge.data.datasources.Resource
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
) = liveData(Dispatchers.IO) {

    emit(Resource.loading())
    val source = databaseQuery.invoke().map { Resource.success(it) }
    emitSource(source)


    val responseStatus = networkCall.invoke()
    if (responseStatus.status == Resource.Status.SUCCESS) {
        saveCallResult(responseStatus.data!!)

    } else if (responseStatus.status == Resource.Status.ERROR) {
        emit(Resource.error(responseStatus.message!!))
        emitSource(source)
    }
}

fun <T> performGetOperation(
    databaseQuery: () -> LiveData<T>
) = liveData(Dispatchers.IO) {
    emit(Resource.loading())
    val source = databaseQuery.invoke().map { Resource.success(it) }
    emitSource(source)
}