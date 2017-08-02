package com.gmail.pasquarelli.brandon.setlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.view.BandInfoFragmentFragment
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.view.SetListFragment
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.view.SongsFragment
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel.SetListsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    var viewPager: ViewPager? = null
    var bottomNavigationView: BottomNavigationView? = null

    var setListsViewModel: SetListsViewModel? = null
    var prevMenuItem: MenuItem? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_set_lists -> {
                viewPager?.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_original_songs -> {
                viewPager?.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cover_songs -> {
                viewPager?.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        initViews()
        initViewModels()
        initViewPager(viewPager)
    }

    fun initViews() {
        viewPager = content_viewpager
        bottomNavigationView = navigation
    }

    fun initViewModels() {
        setListsViewModel = (application as SetListApplication).getSetListsViewmodel()
    }

    fun initViewPager(viewPager: ViewPager?) {
        val pagerAdapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        val setListsFragment: SetListFragment = SetListFragment()
        val songsFragment: SongsFragment = SongsFragment()
        val bandFragment: BandInfoFragmentFragment = BandInfoFragmentFragment()
        pagerAdapter.addFragment(setListsFragment)
        pagerAdapter.addFragment(songsFragment)
        pagerAdapter.addFragment(bandFragment)
        viewPager?.adapter = pagerAdapter

        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem?.isChecked = false
                } else {
                    bottomNavigationView?.menu?.getItem(0)?.isChecked = false
                }
                bottomNavigationView?.menu?.getItem(position)?.isChecked = true
                prevMenuItem = bottomNavigationView?.menu?.getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
