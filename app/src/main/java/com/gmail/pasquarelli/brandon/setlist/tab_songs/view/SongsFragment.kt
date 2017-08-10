package com.gmail.pasquarelli.brandon.setlist.tab_setlists.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.pasquarelli.brandon.setlist.R
import com.gmail.pasquarelli.brandon.setlist.SetListApplication
import com.gmail.pasquarelli.brandon.setlist.ViewPagerAdapter
import com.gmail.pasquarelli.brandon.setlist.tab_songs.new_song.view.NewSongFragment
import com.gmail.pasquarelli.brandon.setlist.tab_songs.viewmodel.SongsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.songs_layout.*

class SongsFragment : Fragment() {

    lateinit var songsViewModel: SongsViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.songs_layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        songsViewModel = (activity.application as SetListApplication).songsViewModel
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }

    fun initViews() {
        button_new_song.setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.content_viewpager, NewSongFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}