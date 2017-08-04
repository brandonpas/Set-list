package com.gmail.pasquarelli.brandon.setlist

import android.app.Application
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel.SetListsViewModel
import timber.log.Timber

class SetListApplication : Application() {

    var setListsViewModel = SetListsViewModel()

    init {
        Timber.plant(Timber.DebugTree())
    }

    fun getSetListsViewmodel() : SetListsViewModel {
        return setListsViewModel
    }
}