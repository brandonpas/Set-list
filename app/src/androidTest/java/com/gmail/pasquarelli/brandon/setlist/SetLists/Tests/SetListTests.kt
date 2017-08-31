package com.gmail.pasquarelli.brandon.setlist.SetLists.Tests

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.gmail.pasquarelli.brandon.setlist.MainActivity
import com.gmail.pasquarelli.brandon.setlist.R
import com.gmail.pasquarelli.brandon.setlist.SetLists.Robots.SetListRobot
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Test
import android.view.WindowManager
import android.content.Context.KEYGUARD_SERVICE
import android.app.KeyguardManager
import android.app.Activity
import android.content.Context
import android.support.test.annotation.UiThreadTest
import org.junit.Before



/**
 * This is where all the tests related to the [com.gmail.pasquarelli.brandon.setlist.tab_setlists.view.SetListFragment]
 * will be added. All specific values used for validation should be declared here. However, there will
 * be no actual logic on how to accomplish the task. That logic is reserved for the [SetListRobot] class.
 */
@RunWith(AndroidJUnit4::class)
class SetListTests {

    val TAG = "SetListTests"

    // Rules
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @UiThreadTest
    @Before
    @Throws(Exception::class)
    fun setUp() {
        val activity = activityRule.getActivity()
        activityRule.runOnUiThread({
            val mKG = activity.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            val mLock = mKG.newKeyguardLock(KEYGUARD_SERVICE)
            mLock.disableKeyguard()

            //turn the screen on
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
        })
    }

    // Tests
    @Test
    fun isTestButtonVisible() {
        val buttonText = activityRule.activity.applicationContext.resources.getString(R.string.button_test_view_model)
        val robot = SetListRobot()
        robot.checkButtonWithTextVisible(buttonText)
    }

    @Test
    fun isNonExistentButtonVisible() {
        val buttonText = 198349834
        val robot = SetListRobot()
        robot.checkViewWithIdNotVisible(buttonText)
    }

    @Test
    fun isListViewEmpty() {
        val robot = SetListRobot()
        refreshActivity()
        robot.verifyListEmpty()
    }

    @Test
    fun doesListUpdateAfterPause() {
        val expectedCountFirstTime = 2
        val expectedCountAfterPause = 4
        val robot = SetListRobot()

        refreshActivity()

        // Add the test data, should add 2 items
        robot.addTestDataToList(expectedCountFirstTime)

        // Switch to the 'Band Info' fragment. This will cause SetListFragmnent.unbind() to be called, which
        // disposes the Disposable.
        robot.clickButton(R.id.navigation_band_info)

        // Switch back to the 'SetLists' fragment. This will cause SetListFragmnent.bind() to be called, which
        // creates the Disposable and re-subscribes to it.
        robot.clickButton(R.id.navigation_set_lists)

        // Click the button to add test data again and ensure there are now new items
        robot.addTestDataToList(expectedCountAfterPause)
    }

    @Test
    fun improvedDoesListUpdateAfterPause() {
        val expectedCountFirstTime = 2
        val expectedCountAfterPause = 4
        refreshActivity()

        SetListRobot().setListCheck {
            addTestDataToList(expectedCountFirstTime)
            clickButton(R.id.navigation_band_info)
            clickButton(R.id.navigation_set_lists)
            addTestDataToList(expectedCountAfterPause)
        }

    }

    @Test
    fun isTestDataLoadedOnClick() {
        val numItemsAddedToList = 2
        val robot = SetListRobot()
        refreshActivity()
        robot.addTestDataToList(numItemsAddedToList)
    }

    fun refreshActivity() {
        activityRule.activity.mainFragment.setListsFragment.setListsViewModel.testArray.clear()
        activityRule.activity.mainFragment.setListsFragment.setListsViewModel.currentCount = 0
        activityRule.activity.finish()
        activityRule.launchActivity(Intent())
    }
}