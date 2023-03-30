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

package com.lillicoder.android.domain.dialogs

import com.lillicoder.android.data.dialogs.DialogEntity

class DialogConverter : DialogConfig.Converter<DialogEntity>, DialogEntity.Converter<DialogConfig> {

    override fun convert(source: DialogConfig): DialogEntity {
        return with(source) {
            DialogEntity(
                0,
                0,
                0,
                title.toString(),
                message.toString(),
                positiveButtonText.toString(),
                negativeButtonText.toString(),
                neutralButtonText.toString(),
                isCancelable,
                isCancelableOnTouchOutside,
                isLinkable,
                shouldEmbed
            )
        }
    }

    override fun convert(source: DialogEntity): DialogConfig {
        return with(source) {
            DialogConfig(
                id,
                iconId,
                layoutId,
                title,
                message,
                positiveButtonText,
                negativeButtonText,
                neutralButtonText,
                isCancelable
            )
        }
    }
}
