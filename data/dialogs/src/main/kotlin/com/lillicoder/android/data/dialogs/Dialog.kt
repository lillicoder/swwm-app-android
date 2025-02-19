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

package com.lillicoder.android.data.dialogs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents the configuration of a dialog.
 */
@Parcelize
data class Dialog(
    val id: Int = 0,
    val title: CharSequence? = null,
    val message: CharSequence? = null,
    val positiveButtonText: CharSequence? = null,
    val negativeButtonText: CharSequence? = null,
    val neutralButtonText: CharSequence? = null,
    val isCancelable: Boolean = true,
    val isCancelableOnTouchOutside: Boolean = true,
    val isLinkable: Boolean = false,
) : Parcelable
