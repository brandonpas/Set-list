package com.gmail.pasquarelli.brandon.setlist.tab_setlists.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel.SetListsViewModel
import com.gmail.pasquarelli.brandon.setlist.R
import com.gmail.pasquarelli.brandon.setlist.utilities.DateTimeFormatUtil
import kotlinx.android.synthetic.main.set_list_row_item.view.*


class SetListAdapter(var viewModel: SetListsViewModel) : RecyclerView.Adapter<SetListAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SetListAdapter.CustomViewHolder {

        val rowItemView: View = LayoutInflater.from(parent?.context).inflate(R.layout.set_list_row_item, parent, false)
        return CustomViewHolder(rowItemView)
    }

    override fun getItemCount(): Int {
        return viewModel.testArray.size
    }

    override fun onBindViewHolder(holder: SetListAdapter.CustomViewHolder?, position: Int) {
        holder?.rowItemView?.set_list_title?.text = viewModel.testArray[position].title
        val itemDate = DateTimeFormatUtil.dateFormatedForLocale(holder?.rowItemView?.context, viewModel.testArray[position].dateTime)
        holder?.rowItemView?.set_list_date?.text = itemDate
    }

    class CustomViewHolder(var rowItemView: View) : RecyclerView.ViewHolder(rowItemView)
}