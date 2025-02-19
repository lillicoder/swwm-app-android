/*
 * Copyright 2025 Scott Weeden-Moody
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

package com.lillicoder.android.feature.dialogs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.lillicoder.android.data.dialogs.Dialog
import com.lillicoder.android.feature.recycler.Bindable

/**
 * Card view that displays a [Dialog].
 */
class DialogCardView : MaterialCardView, Bindable<DialogItemUiState> {
    private var uiState: DialogItemUiState? = null

    private val title: TextView
    private val message: TextView
    private val attributes: TextView
    private val delete: Button
    private val edit: Button

    constructor(context: Context) : this(context, null)

    constructor(
        context: Context,
        attrs: AttributeSet?,
    ) : this(
        context,
        attrs,
        com.google.android.material.R.attr.materialCardViewStyle,
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int,
    ) : super(context, attrs, defStyle) {
        LayoutInflater.from(context).inflate(R.layout.card_view_dialog, this)
        title = findViewById(R.id.title)
        message = findViewById(R.id.message)
        attributes = findViewById(R.id.attributes)

        setOnClickListener { uiState?.onDetail?.invoke() }

        delete = findViewById(R.id.delete)
        delete.setOnClickListener { uiState?.onDelete?.invoke() }

        edit = findViewById(R.id.edit)
        edit.setOnClickListener { uiState?.onEdit?.invoke() }
    }

    override fun bind(source: DialogItemUiState) {
        uiState = source
        title.text = source.dialog.title
        message.text = source.dialog.message
        attributes.text = buildAttributes(source.dialog)
    }

    override fun boundTo(): DialogItemUiState? = uiState

    override fun recycle() {
        uiState = null
        title.text = null
        message.text = null
        attributes.text = null
    }

    /**
     * Builds an attributes display string for the given [Dialog].
     * @param dialog Dialog.
     * @return Attributes display string.
     */
    private fun buildAttributes(dialog: Dialog): String {
        return buildString {
            if (dialog.isCancelable) {
                append(resources.getString(R.string.check_cancelable))
            }

            if (dialog.isCancelableOnTouchOutside) {
                append(" ")
                append(resources.getString(R.string.check_cancelable_on_touch_outside))
            }

            if (dialog.isLinkable) {
                append(" ")
                append(resources.getString(R.string.check_linkable))
            }
        }
    }
}
