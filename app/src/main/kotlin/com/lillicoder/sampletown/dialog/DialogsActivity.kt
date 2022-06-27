package com.lillicoder.sampletown.dialog

import android.content.Context
import android.content.Intent
import com.lillicoder.sampletown.DrawerActivity
import com.lillicoder.sampletown.R

/**
 * Activity-based [DialogsContract.View] for creating and displaying different kinds of dialogs.
 */
class DialogsActivity : DrawerActivity() {

    companion object {

        /**
         * Starts this activity with the given [Context].
         * @param context Starting context.
         */
        fun start(context: Context) {
            val start = Intent(context, DialogsActivity::class.java)
            context.startActivity(start)
        }
    }

    override fun getContentView(): Int {
        return R.layout.activity_dialog_demo
    }

    override fun getNavigationId(): Int {
        return R.id.navigation_dialogs
    }

    override fun onContentViewInflated() {
    }
}
