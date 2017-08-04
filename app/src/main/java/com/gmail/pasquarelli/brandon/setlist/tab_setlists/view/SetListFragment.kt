package com.gmail.pasquarelli.brandon.setlist.tab_setlists.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.gmail.pasquarelli.brandon.setlist.R
import com.gmail.pasquarelli.brandon.setlist.SetListApplication
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.model.SetList
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel.SetListsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.set_lists_layout.*
import timber.log.Timber

class SetListFragment: Fragment() {

    // ViewModels
    lateinit var setListsViewModel: SetListsViewModel

    // Views and adapters
    var listView: RecyclerView? = null
    var button: Button? = null
    lateinit var listAdapter: SetListAdapter

    // RxJava
    lateinit var compDisposable: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.set_lists_layout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setListsViewModel =  (activity.application as SetListApplication).getSetListsViewmodel()
    }

    override fun onResume() {
        super.onResume()
        initViews()
        bind()
    }

    override fun onPause() {
        super.onPause()
        unbind()
    }

    fun initViews() {
        listView = setListRecyclerview
        listAdapter = SetListAdapter(setListsViewModel)
        listView?.adapter = listAdapter
        listView?.layoutManager = LinearLayoutManager(context)


        // Test Button; just to provide an example of using RxJava and observables
        button = test_button
        button?.setOnClickListener {
            setListsViewModel.addTestData()
        }
    }

    fun bind() {
        Timber.v("bind() called")
        compDisposable = CompositeDisposable()
        compDisposable.add(setListsViewModel.getTestVMArray()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::updateAdapter))
    }

    fun unbind() {
        Timber.v("unbind() called")
        compDisposable.dispose()
    }

    fun updateAdapter(list: MutableList<SetList>){
        Timber.v("updateAdapter() called")
        listAdapter.notifyDataSetChanged()
    }

}