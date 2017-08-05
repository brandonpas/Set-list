package com.gmail.pasquarelli.brandon.setlist.SetLists.Robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.test.espresso.action.ViewActions.click
import android.support.v7.widget.RecyclerView
import android.view.View
import com.gmail.pasquarelli.brandon.setlist.R
import org.hamcrest.Matchers.*
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import org.hamcrest.Matcher

/**
 * The robot class should mimic high-level actions that a standard QA team member
 * would perform. It should contain logic for how a value or an action would be taken,
 * but not specific values.
 */
class SetListRobot {

    /**
     * Verify a button is on screen with the text [textToVerify]
     */
    fun checkButtonWithTextVisible(textToVerify: String): SetListRobot {
        onView(withText(textToVerify)).check(matches(isDisplayed()))
        return this
    }

    /**
     * Verify there is no button on the screen with the text [textToVerify]
     */
    fun checkButtonWithTextNotVisible(textToVerify: String): SetListRobot {
        onView(withText(textToVerify)).check(doesNotExist())
        return this
    }

    /**
     * This is only here as an example for how to verify if data is added to a RecyclerView.
     *
     * Function will perform the following:
     * 1. Find the button and click
     * 2. Wait 1 second for data to populate
     * 3. Verify the [expectedAmount] of items are in the list
     */
    fun addTestDataToList(expectedAmount: Int): SetListRobot {
        // click the button to add test data to the recycler view
        onView(withText("Test View Model")).perform(click())

        // wait 1 second for the list to populate
        Thread.sleep(1000L)
        onView(withId(R.id.setListRecyclerview)).check(RecyclerViewItemCountAssertion(expectedAmount))
        return this
    }

    /**
     * Validate that the RecyclerView has no items
     */
    fun verifyListEmpty(): SetListRobot {
        onView(withId(R.id.setListRecyclerview)).check(RecyclerViewItemCountAssertion(0))
        return this
    }

    /**
     * Custom view assertion used to verify the number of items in the RecyclerView
     */
    inner class RecyclerViewItemCountAssertion() : ViewAssertion {
        lateinit var matcher: Matcher<Int>

        // For testing an actual amount
        constructor(expectedAmount: Int) : this() {matcher = `is`(expectedAmount)}

        // For testing greaterThan(1) or lessThan(5)
        constructor(match: Matcher<Int>) : this() {matcher = match}

        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter.itemCount, matcher)
        }
    }
}
