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

package com.lillicoder.recycler

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView.ItemDecoration] that handles default margin spacing between items.
 */
class DefaultSpacingDecoration(
    private val context: Context
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val resources = context.resources
        val bottomMargin = resources.getDimension(R.dimen.list_item_top_margin).toInt()
        val topMargin = when (parent.getChildAdapterPosition(view)) {
            // First item gets much more top spacing then all other views
            0 -> resources.getDimension(R.dimen.default_top_margin).toInt()
            else -> bottomMargin
        }

        outRect.bottom = bottomMargin
        outRect.top = topMargin
    }
}
