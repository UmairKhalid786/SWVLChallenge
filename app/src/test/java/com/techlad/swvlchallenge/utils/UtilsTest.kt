package com.techlad.swvlchallenge.utils

import com.techlad.swvlchallenge.data.responses.FlickerResponse
import org.junit.Assert.*
import org.junit.Test

class UtilsTest {

    @Test
    fun `test Flicker Api Image Replace Url Function`() {
        val photo = FlickerResponse.Photos.Photo("1","a", "s", "t", "f", "s")
        val result = Utils.replaceAll(photo)

        // Check the result
        assertEquals(result, "http://farmf.static.flickr.com/s/1_s.jpg")
    }
}