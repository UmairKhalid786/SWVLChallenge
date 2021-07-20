package com.techlad.swvlchallenge.data.datasources.mappers

import com.techlad.swvlchallenge.data.datasources.Resource


/**
 * Created by Umair on 18,July,2021
 */


//Try to abstract this API so no direct lower class access
interface Mapper<I, O> {
    fun map(input: I?): Resource<O>
}