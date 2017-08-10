package com.gmail.pasquarelli.brandon.setlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(){

    // Fragments
    lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    fun initFragment() {
        val transaction = fragmentManager.beginTransaction()
        mainFragment = MainFragment()
        transaction.replace(R.id.main_fragment_container, mainFragment)
        transaction.commit()
    }
}
