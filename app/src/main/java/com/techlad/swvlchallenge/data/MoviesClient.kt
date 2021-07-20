package com.techlad.swvlchallenge.data

import android.content.Context
import android.util.Log
import org.json.JSONException
import java.io.*
import javax.inject.Inject


/**
 * Created by Umair on 18,July,2021
 */
class MoviesClient @Inject constructor(private val context: Context) {

    companion object {
        private const val JSON_FILE = "movies.json"
        private val TAG = MoviesClient::class.java.simpleName
    }

    fun fetchBlocksDataInString(): String? {

        return try {
            val inputStream: InputStream = context.assets.open(JSON_FILE)
            val streamReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            val stringBuilder = StringBuilder()
            var input: String?

            while (streamReader.readLine().also { input = it } != null) {
                stringBuilder.append(input)
            }

            return stringBuilder.toString()
        } catch (e: JSONException) {
            Log.e(TAG, e.toString())
            null
        } catch (e: UnsupportedEncodingException) {
            Log.e(TAG, e.toString())
            null
        } catch (e: IOException) {
            Log.e(TAG, e.toString())
            null
        }
    }
}