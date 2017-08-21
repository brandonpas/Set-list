package com.gmail.pasquarelli.brandon.setlist.tab_bandinfo.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.pasquarelli.brandon.setlist.R

/**  */
class BandInfoFragmentFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.band_info_layout, container, false)
    }
}