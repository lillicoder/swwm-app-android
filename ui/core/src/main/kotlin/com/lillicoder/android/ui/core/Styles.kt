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

package com.lillicoder.android.ui.core

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.AttrRes

/**
 * Utilities for accessing and resolving themes and style related values.
 */
object Styles {

    /**
     * Gets the [Drawable] corresponding to the given attribute value.
     * @param context [Context] to resolve theme with.
     * @param attribute Drawable attribute to resolve.
     * @return Drawable or null if there is no drawable corresponding to the given attribute.
     */
    fun drawableAttribute(context: Context, @AttrRes attribute: Int): Drawable? {
        val theme = context.theme
        val resolved = theme.obtainStyledAttributes(intArrayOf(attribute))
        return resolved.getDrawable(0)
    }
}
