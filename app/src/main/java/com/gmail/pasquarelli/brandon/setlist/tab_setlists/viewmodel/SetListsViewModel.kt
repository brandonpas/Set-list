package com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel

import android.util.Log
import com.gmail.pasquarelli.brandon.setlist.tab_setlists.model.SetList
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

class SetListsViewModel {
    var testArray: MutableList<SetList> = mutableListOf()
    var arrayState: BehaviorSubject<MutableList<SetList>> = BehaviorSubject.create()

    fun addTestData(): Completable {
        return Completable.fromAction {
            Timber.v("addTestData() called on background thread: ${Thread.currentThread().id}")
            val setList1 = SetList(1501466400000L, "${testArray.size + 1}: Local Bar")
            testArray.add(setList1)
            val setList2 = SetList(1501729200000L, "${testArray.size + 1}: Another Bar")
            testArray.add(setList2)
            updateArray(testArray)
        }
    }

    fun getTestVMArray(): Observable<MutableList<SetList>> {
        Timber.v("getTestVMArray called")
        return arrayState
    }

    fun updateArray(newList: MutableList<SetList>) {
        Timber.v("updateArray called")
        arrayState.onNext(newList)
    }
}