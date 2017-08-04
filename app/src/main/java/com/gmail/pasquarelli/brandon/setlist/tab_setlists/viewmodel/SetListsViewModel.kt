package com.gmail.pasquarelli.brandon.setlist.tab_setlists.viewmodel

import com.gmail.pasquarelli.brandon.setlist.tab_setlists.model.SetList
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

class SetListsViewModel {
    var testArray: MutableList<SetList> = mutableListOf()
    var arrayState: BehaviorSubject<MutableList<SetList>> = BehaviorSubject.create()
    val maxBeforeError = 4
    var currentCount = 0

    fun addTestData(): Completable {
        return Completable.fromAction {
            Timber.v("addTestData() called on background thread: ${Thread.currentThread().id}")

            // Intentionally throw an error to show that the Completable can be handled differently on error
            if (currentCount >= maxBeforeError) throw RuntimeException("Max hit")

            val setList1 = SetList(1501466400000L, "${testArray.size + 1}: Gramercy Theatre")
            testArray.add(setList1)
            currentCount++

            var date = 1501729200000L
            if (currentCount == 1) {
                date = System.currentTimeMillis()
            }
            val setList2 = SetList(date, "${testArray.size + 1}: Terminal 5")
            testArray.add(setList2)
            currentCount++

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