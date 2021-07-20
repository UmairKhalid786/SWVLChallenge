package com.techlad.swvlchallenge

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Umair on 20,July,2021
 */
/*
* Note: Currently, Hilt only supports Android instrumentation and Robolectric tests
* (although, see here for limitations when running Robolectric tests via Android Studio).
* In addition, Hilt cannot be used in vanilla JVM tests, but it does not prevent you from writing
* these tests as you would normally.
* */

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity()