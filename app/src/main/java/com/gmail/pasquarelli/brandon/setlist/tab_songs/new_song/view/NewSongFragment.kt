package com.gmail.pasquarelli.brandon.setlist.tab_songs.new_song.view

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.pasquarelli.brandon.setlist.R
import com.gmail.pasquarelli.brandon.setlist.SetListApplication
import com.gmail.pasquarelli.brandon.setlist.tab_songs.new_song.viewmodel.NewSongViewModel
import kotlinx.android.synthetic.main.new_song_layout.*

class NewSongFragment : Fragment() {

    lateinit var newSongViewModel: NewSongViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.new_song_layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newSongViewModel = (activity.application as SetListApplication).newSongViewModel
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }

    fun initViews() {
        button_cancel_new_song.setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(this)
            transaction.commit()
        }
    }
}