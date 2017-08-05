package com.gmail.pasquarelli.brandon.setlist.tab_setlists.model

import com.gmail.pasquarelli.brandon.setlist.tab_songs.model.Song

/**
 * Created by brand on 8/1/2017.
 */
data class SetList(var dateTime: Long, var title: String) {
    var songList: MutableList<Song> = mutableListOf()
}