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

package com.lillicoder.android.data.dialogs

/**
 * Constants for all tables and their related names and columns.
 */
object Tables {

    /**
     * Constants for the dialogs table.
     */
    object Dialogs {

        const val TABLE_NAME = "dialogs"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_MESSAGE = "message"
        const val COLUMN_POSITIVE_BUTTON_TEXT = "positive_button_text"
        const val COLUMN_NEGATIVE_BUTTON_TEXT = "negative_button_text"
        const val COLUMN_NEUTRAL_BUTTON_TEXT = "neutral_button_text"
        const val COLUMN_IS_CANCELABLE = "is_cancelable"
        const val COLUMN_IS_CANCELABLE_ON_TOUCH_OUTSIDE = "is_cancelable_on_touch_outside"
        const val COLUMN_IS_LINKABLE = "is_linkable"
    }
}
