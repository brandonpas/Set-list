package com.gmail.pasquarelli.brandon.setlist.tab_setlists

import com.gmail.pasquarelli.brandon.setlist.Song

/**
 * Created by brand on 8/1/2017.
 */
data class SetList(var dateTime: Long, var title: String) {
    var songList: List<Song> = emptyList()
}