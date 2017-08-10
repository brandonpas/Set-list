package com.gmail.pasquarelli.brandon.setlist.tab_setlists.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.gmail.pasquarelli.brandon.setlist.R
import com.gmail.pasquarelli.brandon.setlist.SetListApplication
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.model.SetList
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel.SetListsViewModel
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
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
        setListsViewModel =  (activity.application as SetListApplication).setListsViewModel
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
            Timber.v("main thread ${Thread.currentThread().id}")
            setListsViewModel.addTestData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(SetListSaveResult())
        }
    }

    /**
     * Subscribe to any observables that the [SetListFragment] needs.
     */
    fun bind() {
        Timber.v("bind() called")
        compDisposable = CompositeDisposable()
        compDisposable.add(setListsViewModel.getTestVMArray()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::updateAdapter))
    }

    /**
     * Clear all subscriptions.
     */
    fun unbind() {
        Timber.v("unbind() called")
        compDisposable.dispose()
    }

    /**
     * Callback for when the [setListsViewModel] array is updated (observable emits a change).
     */
    fun updateAdapter(list: MutableList<SetList>){
        Timber.v("updateAdapter() called")
        listAdapter.notifyDataSetChanged()
    }

    /**
     * Standard API to display a toast message
     *
     * @param message String to be displayed in the Toast message
     * @param duration Either [Toast.LENGTH_LONG] or [Toast.LENGTH_SHORT]
     */
    fun displayToast(message: String, duration: Int) {
        Toast.makeText(activity, message, duration).show()
    }

    /**
     * An observer to be used when saving a new set list. Use as a callback to do something when the new list is saved
     * or encounters an error while saving.
     */
    inner class SetListSaveResult : CompletableObserver {
        // Callback for when the setListsViewModel addTestData() method is called and the Completable completes successfully.
        override fun onComplete() { Timber.v("Saved on background thread; now back on main thread. Thread ${Thread.currentThread().id}") }

        override fun onSubscribe(d: Disposable) {}

        // Callback for when the setListsViewModel addTestData() method is called and the Completable encounters an error.
        override fun onError(e: Throwable) {
            Timber.v("Error on background thread; now back on main thread. Thread ${Thread.currentThread().id}")
            Timber.v("Error Message: ${e.message}")
            displayToast("Gracefully encountered error while saving.", Toast.LENGTH_LONG)
        }
    }

}