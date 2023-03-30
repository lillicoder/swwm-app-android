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

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Tables.Dialogs.TABLE_NAME)
data class DialogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Tables.Dialogs.COLUMN_ID)
    val id: Int,

    // TODO Compiles that shuffle ID orders will break this, use a canonical ID separate from res/
    @ColumnInfo(name = Tables.Dialogs.COLUMN_ICON_ID)
    val iconId: Int,

    // TODO Compiles that shuffle ID orders will break this, use a canonical ID separate from res/
    @ColumnInfo(name = Tables.Dialogs.COLUMN_LAYOUT_ID)
    val layoutId: Int,

    @ColumnInfo(name = Tables.Dialogs.COLUMN_TITLE)
    val title: String?,

    @ColumnInfo(name = Tables.Dialogs.COLUMN_MESSAGE)
    val message: String?,

    @ColumnInfo(name = Tables.Dialogs.COLUMN_POSITIVE_BUTTON_TEXT)
    val positiveButtonText: String?,

    @ColumnInfo(name = Tables.Dialogs.COLUMN_NEGATIVE_BUTTON_TEXT)
    val negativeButtonText: String?,

    @ColumnInfo(name = Tables.Dialogs.COLUMN_NEUTRAL_BUTTON_TEXT)
    val neutralButtonText: String?,

    @ColumnInfo(name = Tables.Dialogs.COLUMN_IS_CANCELABLE)
    val isCancelable: Boolean,

    @ColumnInfo(name = Tables.Dialogs.COLUMN_IS_CANCELABLE_ON_TOUCH_OUTSIDE)
    val isCancelableOnTouchOutside: Boolean,

    @ColumnInfo(name = Tables.Dialogs.COLUMN_IS_LINKABLE)
    val isLinkable: Boolean,

    @ColumnInfo(name = Tables.Dialogs.COLUMN_SHOULD_EMBED)
    val shouldEmbed: Boolean
) {

    interface Converter<T> {

        /**
         * Converts the given source type to an equivalent [DialogEntity].
         * @param source Source type.
         * @return Dialog entity.
         */
        fun convert(source: T): DialogEntity
    }
}
