package com.lillicoder.dialogs

import com.lillicoder.lifecycle.LifecyclePresenter

/**
 * Contracts for a UI layer to create and display various kinds of dialogs.
 */
interface DialogsContract {

    interface Presenter : LifecyclePresenter

    interface View
}
