package com.lillicoder.sampletown.lifecycle

import android.os.Bundle

/**
 * Presenter that obeys an Android-centric lifecycle.
 */
interface LifecyclePresenter {

    /**
     * Called when this presenter is created.
     */
    fun create() {}

    /**
     * Called when this presenter is destroyed.
     */
    fun destroy() {}

    /**
     * Called when this presenter needs to save its state due toa a lifecycle event.
     * @param bundle [Bundle] to save state to.
     */
    fun saveState(bundle: Bundle) {}

    /**
     * Called when this presenter is started.
     */
    fun start() {}

    /**
     * Called when this presenter is stopped.
     */
    fun stop() {}
}
