package com.gmail.pasquarelli.brandon.setlist.SetLists.Robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.action.ViewActions.click
import com.gmail.pasquarelli.brandon.setlist.R
import com.gmail.pasquarelli.brandon.setlist.RecyclerViewItemCountAssertion
import com.gmail.pasquarelli.brandon.setlist.Robot

/**
 * The robot class should mimic high-level actions that a standard QA team member
 * would perform. It should contain logic for how a value or an action would be taken,
 * but not specific values.
 */
class SetListRobot : Robot() {

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


    fun setListCheck(func: SetListRobot.() -> Unit) = SetListRobot().apply { func() }

}
