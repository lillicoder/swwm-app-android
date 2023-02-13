package com.lillicoder.dialogs

/**
 * [DialogsContract.Presenter] for handling events from a [DialogsContract.View].
 */
class DialogsPresenter(
    private val view: DialogsContract.View
) : DialogsContract.Presenter
