package com.techlad.swvlchallenge.ui.detail

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.isGone
import com.isVisible
import com.techlad.swvlchallenge.R
import com.techlad.swvlchallenge.launchFragmentInHiltContainer
import com.techlad.swvlchallenge.ui.home.MoviesListFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class MovieDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testToolbarVisibility() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<MovieDetailFragment> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.primary_details_nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(this.requireView(), navController)

            try {
                Espresso.onView(ViewMatchers.withId(R.id.toolbarLl)).isVisible()
                // View is in hierarchy
            } catch (e: NoMatchingViewException) {
                // View is not in hierarchy
            }
        }
    }

    @Test
    fun testDetailHeaderVisibility() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<MovieDetailFragment> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.primary_details_nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(this.requireView(), navController)

            try {
                Espresso.onView(ViewMatchers.withId(R.id.detailHeader)).isVisible()
                // View is in hierarchy
            } catch (e: NoMatchingViewException) {
                // View is not in hierarchy
            }
        }
    }

    @Test
    fun testProgressVisibility() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<MovieDetailFragment> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.primary_details_nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(this.requireView(), navController)

            try {
                Espresso.onView(ViewMatchers.withId(R.id.searchProgBar)).isVisible()
                // View is in hierarchy
            } catch (e: NoMatchingViewException) {
                // View is not in hierarchy
            }
        }
    }

    @Test
    fun testNoRecordLlVisibility() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<MovieDetailFragment> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.primary_details_nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(this.requireView(), navController)

            try {
                Espresso.onView(ViewMatchers.withId(R.id.noRecordLl)).isGone()
                // View is in hierarchy
            } catch (e: NoMatchingViewException) {
                // View is not in hierarchy
            }
        }
    }
}