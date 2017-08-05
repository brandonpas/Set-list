package com.gmail.pasquarelli.brandon.setlist.SetLists.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.gmail.pasquarelli.brandon.setlist.MainActivity
import com.gmail.pasquarelli.brandon.setlist.R
import com.gmail.pasquarelli.brandon.setlist.SetLists.Robots.SetListRobot
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Test

/**
 * This is where all the tests related to the [com.gmail.pasquarelli.brandon.setlist.tab_setlists.view.SetListFragment]
 * will be added. All specific values used for validation should be declared here. However, there will
 * be no actual logic on how to accomplish the task. That logic is reserved for the [SetListRobot] class.
 */
@RunWith(AndroidJUnit4::class)
class SetListTests {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)
    val robot = SetListRobot()


    @Test
    fun isTestButtonVisible(){
        val buttonText = activityRule.activity.applicationContext.resources.getString(R.string.button_test_view_model)
        robot.checkButtonWithTextVisible(buttonText)
    }

    @Test
    fun isNonExistentButtonVisible(){
        val buttonText = "This button doesn't exist"
        robot.checkButtonWithTextNotVisible(buttonText)
    }

    @Test
    fun isListViewEmpty() {
        robot.verifyListEmpty()
    }

    @Test
    fun isTestDataLoadedOnClick() {
        val numItemsAddedToList = 2
        robot.addTestDataToList(numItemsAddedToList)
    }
}