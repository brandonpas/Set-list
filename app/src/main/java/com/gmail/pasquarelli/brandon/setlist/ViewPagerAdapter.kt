package com.gmail.pasquarelli.brandon.setlist

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(manager: FragmentManager?) : FragmentPagerAdapter(manager) {

    var fragmentList: MutableList<Fragment> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

}


