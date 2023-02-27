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
import androidx.appcompat.widget.LinearLayoutCompat
import com.lillicoder.android.domain.dialogs.DialogConfig
import com.lillicoder.android.ui.recycler.Bindable

/**
 * list item view that displays a [DialogConfig].
 */
class DialogListItemView : LinearLayoutCompat, Bindable<DialogConfig> {

    private var configuration: DialogConfig? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        orientation = VERTICAL

        LayoutInflater.from(context).inflate(R.layout.list_item_dialog, this)
    }

    override fun bind(source: DialogConfig) {
        configuration = source

        // TODO Update views
    }

    override fun boundTo(): DialogConfig? = configuration

    override fun recycle() {
        configuration = null

        // TODO Update views
    }
}
