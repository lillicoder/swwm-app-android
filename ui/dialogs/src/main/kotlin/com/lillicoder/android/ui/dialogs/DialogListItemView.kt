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

package com.lillicoder.android.ui.dialogs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.lillicoder.android.domain.dialogs.DialogConfig
import com.lillicoder.android.ui.recycler.Bindable

/**
 * list item view that displays a [DialogConfig].
 */
class DialogListItemView : RelativeLayout, Bindable<DialogConfig> {

    private var configuration: DialogConfig? = null

    private val title: TextView
    private val message: TextView
    private val attributes: TextView
    private val delete: ImageView
    private val edit: ImageView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        LayoutInflater.from(context).inflate(R.layout.list_item_dialog, this)
        title = findViewById(R.id.title)
        message = findViewById(R.id.message)
        attributes = findViewById(R.id.attributes)

        delete = findViewById(R.id.delete)
        delete.setOnClickListener {
            Toast.makeText(context, "TODO Delete", Toast.LENGTH_SHORT).show()
        }

        edit = findViewById(R.id.edit)
        edit.setOnClickListener {
            Toast.makeText(context, "TODO Edit", Toast.LENGTH_SHORT).show()
        }
    }

    override fun bind(source: DialogConfig) {
        configuration = source
        title.text = source.title
        message.text = source.message
        attributes.text = buildAttributes(source)
    }

    override fun boundTo(): DialogConfig? = configuration

    override fun recycle() {
        configuration = null
        title.text = null
        message.text = null
        attributes.text = null
    }

    /**
     * Builds an attributes display string for the given [DialogConfig].
     * @param config Dialog configuration.
     * @return Attributes display string.
     */
    private fun buildAttributes(config: DialogConfig): String {
        return buildString {
            if (config.isCancelable) {
                append(resources.getString(R.string.check_cancelable))
            }

            if (config.isCancelableOnTouchOutside) {
                append(" ")
                append(resources.getString(R.string.check_cancelable_on_touch_outside))
            }

            if (config.shouldEmbed) {
                append(" ")
                append(resources.getString(R.string.check_embedded))
            }

            if (config.isLinkable) {
                append(" ")
                append(resources.getString(R.string.check_linkable))
            }
        }
    }
}
