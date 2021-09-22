package com.solarexsoft.rxpaging3

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.Flowable

fun <T> Flowable<T>.auto(
    lifecycleOwner: LifecycleOwner,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): Flowable<T> {
    return doOnLifecycle({
        lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, actualEvent: Lifecycle.Event) {
                if (event == actualEvent) it.cancel()
            }
        })
    }, { }, { })
}