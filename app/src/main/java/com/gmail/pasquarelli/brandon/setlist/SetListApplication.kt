package com.gmail.pasquarelli.brandon.setlist

import android.app.Application
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel.SetListsViewModel
import com.gmail.pasquarelli.brandon.setlist.tab_songs.new_song.viewmodel.NewSongViewModel
import com.gmail.pasquarelli.brandon.setlist.tab_songs.viewmodel.SongsViewModel
import timber.log.Timber

class SetListApplication : Application() {

    val setListsViewModel = SetListsViewModel()
    val songsViewModel = SongsViewModel()

    val newSongViewModel = NewSongViewModel()

    init {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // Initialize Crashlytics
        }
    }
}