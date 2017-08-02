package com.gmail.pasquarelli.brandon.setlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.SetListsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    var setListsViewModel: SetListsViewModel? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_set_lists -> {
                message.setText(R.string.title_set_lists)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_original_songs -> {
                message.setText(R.string.title_songs)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cover_songs -> {
                message.setText(R.string.title_band_info)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        initViewModels()
    }

    fun initViewModels() {
        setListsViewModel = (application as SetListApplication).getSetListsViewmodel()
    }
}
