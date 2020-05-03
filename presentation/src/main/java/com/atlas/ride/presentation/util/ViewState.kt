package com.atlas.ride.presentation.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * A wrapper around a mutable live data. By enforcing an initial state, the value is always valid
 * (non-null) for an accessing observer. The data within can still be observered/managed as with a
 * normal live data object.
 */
class ViewState<T>(initial: T) {
    /** The currently stored value. The null check can be skipped because of the initial state. */
    var value: T
        get() = liveData.value!!
        set(value) { liveData.value = value }

    // Create the backing store with the given initial value.
    private val liveData = MutableLiveData<T>().apply {
        value = initial
    }

    /**
     * Observe the stored state value, running [callback] when the value is changed. The callback
     * will be automatically removed when [owner] expires. The returned [Observer] can also be used
     * passed to [removeObserver] to destroy it before that point.
     */
    fun observe(owner: LifecycleOwner, callback: (T) -> Unit): Observer<T> {
        val observer = Observer<T> { callback(it) }
        liveData.observe(owner, observer)

        return observer
    }

    /** Expose the single observer removal function from the live data. */
    fun removeObserver(observer: Observer<T>) = liveData.removeObserver(observer)
    /** Allow the live data to remove all observers under the given lifecycle. */
    fun removeObesrvers(owner: LifecycleOwner) = liveData.removeObservers(owner)
}
