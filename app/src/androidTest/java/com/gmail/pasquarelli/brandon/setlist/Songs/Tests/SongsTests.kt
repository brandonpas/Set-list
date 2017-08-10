package com.gmail.pasquarelli.brandon.setlist.Songs.Tests

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.gmail.pasquarelli.brandon.setlist.MainActivity
import com.gmail.pasquarelli.brandon.setlist.R
import com.gmail.pasquarelli.brandon.setlist.Songs.Robots.SongsRobot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongsTests {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun switchToSongsTab() {
        SongsRobot().songsCheck {
            clickButton(R.id.navigation_songs)
        }
    }

    /**
     * 1. Make sure the "New Song" button is visible and click it.
     * 2. Verify "New Song" button is no longer visible and the "Cancel" new song button is now displayed.
     * 3. Click the "Cancel" new song button, make sure the "Cancel" button is gone and the "New Song" button is back.
     */
    @Test
    fun doesNewSongsFragmentReplaceSongsFragment() {
        val newSongButton = R.id.button_new_song
        val cancelNewSongButton = R.id.button_cancel_new_song

        SongsRobot().songsCheck {
            checkViewWithIdVisible(newSongButton)
            clickButton(newSongButton)

            checkViewWithIdNotVisible(newSongButton)
            checkViewWithIdVisible(cancelNewSongButton)
            clickButton(cancelNewSongButton)

            checkViewWithIdNotVisible(cancelNewSongButton)
            checkViewWithIdVisible(newSongButton)
        }
    }

}