package com.gmail.pasquarelli.brandon.setlist

import android.app.Application
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel.SetListsViewModel
import timber.log.Timber

class SetListApplication : Application() {

    var setListsViewModel = SetListsViewModel()

    init {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // Initialize Crashlytics
        }
    }

    fun getSetListsViewmodel() : SetListsViewModel {
        return setListsViewModel
    }
}