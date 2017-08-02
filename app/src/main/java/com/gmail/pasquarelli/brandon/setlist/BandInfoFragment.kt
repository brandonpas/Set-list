package com.gmail.pasquarelli.brandon.setlist.tab_setlists.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.pasquarelli.brandon.setlist.R

/**
 * Created by brand on 8/1/2017.
 */
class BandInfoFragmentFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.band_info_layout, container, false)
    }
}