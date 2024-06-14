/*
 * Copyright 2024 Scott Weeden-Moody
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

package com.lillicoder.android.ui.about

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.lillicoder.android.data.device.BuildInfo
import com.lillicoder.android.ui.recycler.Bindable

/**
 * List item view that displays a [BuildInfo].
 */
class AboutListItemView : LinearLayoutCompat, Bindable<com.lillicoder.android.data.device.BuildInfo> {
    private val title: TextView
    private val body: TextView

    private var information: com.lillicoder.android.data.device.BuildInfo? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int,
    ) : super(context, attrs, defStyle) {
        orientation = VERTICAL

        LayoutInflater.from(context).inflate(R.layout.list_item_about, this)
        title = findViewById(R.id.title)
        body = findViewById(R.id.body)
    }

    override fun bind(source: com.lillicoder.android.data.device.BuildInfo) {
        information = source

        title.setText(source.name)
        body.text = source.value
    }

    override fun boundTo() = information

    override fun recycle() {
        information = null
        title.text = null
        body.text = null
    }
}
