package com.gmail.pasquarelli.brandon.setlist.Songs.Robots

import com.gmail.pasquarelli.brandon.setlist.Robot

class SongsRobot : Robot() {

    fun songsCheck(func: SongsRobot.() -> Unit) = SongsRobot().apply { func() }
}