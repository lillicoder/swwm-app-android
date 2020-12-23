package com.lillicoder.sampletown

import android.content.Context
import android.content.Intent

class SecondaryActivity : DrawerActivity() {

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, SecondaryActivity::class.java))
        }

    }

    override fun getContentView(): Int {
        return R.layout.activity_secondary
    }

}