package com

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher


/**
 * Created by Umair on 18,July,2021
 */

fun Int.shouldHaveTextAtPosition(text:String, position: Int) {


//    onView(withId(R.id.recylcer_view))
//        .perform(RecyclerViewActions.actionOnItem(
//            hasDescendant(withText("Text of item you want to scroll to")),
//            click()));

//    onView(withId(this))
//        .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
//        .check(matches(atPosition(position, hasDescendant(withText(text)))))
}

fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(recyclerView: RecyclerView): Boolean {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
            return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
        }
    }
}

fun ViewInteraction.isGone() = getViewAssertion(ViewMatchers.Visibility.GONE)

fun ViewInteraction.isVisible() = getViewAssertion(ViewMatchers.Visibility.VISIBLE)

fun ViewInteraction.isInvisible() = getViewAssertion(ViewMatchers.Visibility.INVISIBLE)

private fun getViewAssertion(visibility: ViewMatchers.Visibility): ViewAssertion? {
    return ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility))
}