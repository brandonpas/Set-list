package com.gmail.pasquarelli.brandon.setlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.gmail.pasquarelli.brandon.setlist.tab_bandinfo.view.BandInfoFragmentFragment
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.view.SetListFragment
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.view.SongsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    // Activity variables
    lateinit var prevMenuItem: MenuItem

    // Fragments
    lateinit var setListsFragment: SetListFragment
    lateinit var songsFragment: SongsFragment
    lateinit var bandFragment: BandInfoFragmentFragment

    // Views, adapters, and listeners
    lateinit var viewPager: ViewPager
    lateinit var bottomNavigationView: BottomNavigationView
    val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_set_lists -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_original_songs -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cover_songs -> {
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewPager(viewPager)
    }

    /**
     * Initialize views, attach listeners, etc. here.
     */
    fun initViews() {

        // View objects
        viewPager = content_viewpager
        bottomNavigationView = navigation
        prevMenuItem = bottomNavigationView.menu.getItem(0)

        // Fragments
        setListsFragment = SetListFragment()
        songsFragment = SongsFragment()
        bandFragment = BandInfoFragmentFragment()

        // Listeners
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    /**
     * Setup the view pager and view pager adapter for the fragments. This provides a smooth sliding animation when
     * switching between the menu items.
     */
    fun initViewPager(viewPager: ViewPager) {
        val pagerAdapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(setListsFragment)
        pagerAdapter.addFragment(songsFragment)
        pagerAdapter.addFragment(bandFragment)
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                prevMenuItem.isChecked = false
                bottomNavigationView.menu.getItem(position)?.isChecked = true
                prevMenuItem = bottomNavigationView.menu.getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
