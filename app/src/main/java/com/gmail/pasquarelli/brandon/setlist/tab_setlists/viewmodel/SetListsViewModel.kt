package com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel

import com.gmail.pasquarelli.brandon.setlist.Song

class SetListsViewModel {
    var testArray: MutableList<Song> = mutableListOf()

    fun addTestData(){
        val song1 = Song("Song 1", 293)
        val song2 = Song("Song 2", 314)
        testArray.add(song1)
        testArray.add(song2)
    }

    fun getTestVMArray(): MutableList<Song> = testArray
}