package com.lillicoder.dialogs

private const val TAG_CREATE_DIALOG_FRAGMENT = "tag-create-dialog-fragment"

/**
 * [DialogsContract.Presenter] for handling events from a [DialogsContract.View].
 */
class DialogsPresenter(
    private val view: DialogsContract.View
) : DialogsContract.Presenter {

    override fun newDialog() {
        val fragment = CreateDialogFragment.newInstance()
        view.show(fragment, TAG_CREATE_DIALOG_FRAGMENT)
    }
}
