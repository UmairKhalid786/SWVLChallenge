package com.techlad.swvlchallenge.ui.home

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.isGone
import com.isVisible
import com.techlad.swvlchallenge.R
import com.techlad.swvlchallenge.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class MoviesListFragmentTest {
//
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun testRecyclerViewVisibility() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<MoviesListFragment> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.primary_details_nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(this.requireView(), navController)

            try {
                onView(withId(R.id.item_list)).isVisible()
                // View is in hierarchy
            } catch (e: NoMatchingViewException) {
                // View is not in hierarchy
            }
        }
    }

    @Test
    fun testNoRecordViewVisibility() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<MoviesListFragment> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.primary_details_nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(this.requireView(), navController)

            try {
                onView(withId(R.id.noRecordLl)).isGone()
                // View is in hierarchy
            } catch (e: NoMatchingViewException) {
                // View is not in hierarchy
            }
        }
    }

    @Test
    fun testNavigationToInGameScreen() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInHiltContainer<MoviesListFragment> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.primary_details_nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(this.requireView(), navController)
        }

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.item_list_fragment)
    }
}