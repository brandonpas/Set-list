package com.gmail.pasquarelli.brandon.setlist

import android.app.Application
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.SetListsViewModel

/**
 * Created by brand on 8/1/2017.
 */
class SetListApplication : Application() {

    var setListsViewModel: SetListsViewModel? = null

    fun getSetListsViewmodel() : SetListsViewModel? {
        if (setListsViewModel == null) setListsViewModel = SetListsViewModel()
        return setListsViewModel
    }
}