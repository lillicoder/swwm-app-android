package com.lillicoder.sampletown.dialog

import androidx.fragment.app.Fragment
import com.lillicoder.sampletown.lifecycle.LifecyclePresenter

/**
 * Contracts for a UI layer to create and display various kinds of dialogs.
 */
interface DialogsContract {

    interface Presenter : LifecyclePresenter {

        /**
         * Starts creation of a new dialog.
         */
        fun newDialog()
    }

    interface View {

        /**
         * Shows the given [Fragment] on this view using the given tag.
         * @param fragment Fragment tos how.
         * @param tag Fragment tag.
         */
        fun show(fragment: Fragment, tag: String)
    }
}