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

/**
 * Utility that can convert between instances of [Dialog] and [DialogEntity].
 */
class DialogTypeConverter {
    /**
     * Converts the given [Dialog] to an equivalent [DialogEntity].
     * @param source Config to convert.
     * @return Dialog entity.
     */
    fun convert(source: Dialog): DialogEntity {
        return with(source) {
            DialogEntity(
                id,
                title.toString(),
                message.toString(),
                positiveButtonText.toString(),
                negativeButtonText.toString(),
                neutralButtonText.toString(),
                isCancelable,
                isCancelableOnTouchOutside,
                isLinkable,
            )
        }
    }

    /**
     * Converts the given [DialogEntity] to an equivalent [Dialog].
     * @param source Entity to convert.
     * @return Dialog config.
     */
    fun convert(source: DialogEntity): Dialog {
        return with(source) {
            Dialog(
                id,
                title,
                message,
                positiveButtonText,
                negativeButtonText,
                neutralButtonText,
                isCancelable,
                isCancelableOnTouchOutside,
                isLinkable,
            )
        }
    }
}
