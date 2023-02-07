package com.lillicoder.dialogs

import com.lillicoder.lifecycle.LifecyclePresenter

interface CreateDialogContract {

    interface Presenter : LifecyclePresenter {

        fun createDialog()
    }

    interface View {

        /**
         * Shows the configuration in the given [AlertDialogFragment.Builder] on this view.
         * @param builder Builder to show.
         */
        fun showConfiguration(builder: AlertDialogFragment.Builder)
    }
}
