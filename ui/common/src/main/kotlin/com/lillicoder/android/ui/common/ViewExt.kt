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

package com.lillicoder.android.ui.common

import android.util.TypedValue
import android.view.View
import androidx.annotation.AnyRes
import androidx.annotation.AttrRes

/**
 * Resolves the given attribute ID into its concrete resource ID.
 * @param id Attribute ID to resolve.
 * @return Concrete resource ID mapped to the given attribute ID.
 */
@AnyRes
private fun View.resolveAttribute(
    @AttrRes id: Int,
): Int =
    with(TypedValue()) {
        context.theme.resolveAttribute(id, this, true)
        return resourceId
    }

/**
 * Sets this view's padding to match the default side padding and top/bottom padding
 * for items displayed in a standard list.
 */
fun View.setDefaultListItemPadding() {
    val sidePadding = context.resources.getDimension(R.dimen.default_side_margin).toInt()
    val topBottomPadding = context.resources.getDimension(R.dimen.default_divider_margin).toInt()
    setPadding(sidePadding, topBottomPadding, sidePadding, topBottomPadding)
}

/**
 * Sets this view's background to the drawable mapped to [android.R.attr.selectableItemBackground].
 */
fun View.setSelectableBackground() {
    val backgroundId = resolveAttribute(android.R.attr.selectableItemBackground)
    setBackgroundResource(backgroundId)
}
