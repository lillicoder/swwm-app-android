/*
 * Copyright 2023 Scott Weeden-Moody
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this project except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lillicoder.android.ui.lifecycle

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
