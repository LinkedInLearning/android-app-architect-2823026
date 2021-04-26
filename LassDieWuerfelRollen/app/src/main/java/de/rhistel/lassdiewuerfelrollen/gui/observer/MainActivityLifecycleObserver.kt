package de.rhistel.lassdiewuerfelrollen.gui.observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import de.rhistel.lassdiewuerfelrollen.settings.LIFECYCLE_OBSERVER_TAG

/**
 * Ueberwacht den Lebenszyklus der [MainActivity].
 * Dort wird dieser Observer auch zuewiesen
 */
class MainActivityLifecycleObserver : LifecycleObserver{

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateEvent(){
        Log.d(LIFECYCLE_OBSERVER_TAG,"onCreate()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStartEvent(){
        Log.d(LIFECYCLE_OBSERVER_TAG,"onStart()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeEvent(){
        Log.d(LIFECYCLE_OBSERVER_TAG,"onResume()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseEvent(){
        Log.d(LIFECYCLE_OBSERVER_TAG,"onPause()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopEvent(){
        Log.d(LIFECYCLE_OBSERVER_TAG,"onStop()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyEvent(){
        Log.d(LIFECYCLE_OBSERVER_TAG,"onDestroy()")
    }


}