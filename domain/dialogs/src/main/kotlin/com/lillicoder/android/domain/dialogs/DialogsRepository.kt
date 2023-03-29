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

import android.content.Context
import com.lillicoder.android.data.dialogs.DialogsDao
import com.lillicoder.android.data.dialogs.DialogsDatabase

/**
 * Repository for dialog configurations.
 */
class DialogsRepository(
    context: Context,
    private val dialogsDao: DialogsDao = DialogsDatabase.getInstance(context).dialogsDao()
) {

    /**
     * Gets the [DialogConfig] for the given ID.
     * @param id Configuration ID.
     * @return Dialog configuration or null if there is no configuration for the given ID.
     */
    fun configuration(id: Int): DialogConfig? {
        val entity = dialogsDao.dialog(id)
        return entity?.let { DialogConverter().convert(it) }
    }

    /**
     * Gets a list of all available [DialogConfig].
     * @return Dialog configurations.
     */
    fun configurations(): List<DialogConfig> {
        val entities = dialogsDao.dialogs()
        val converter = DialogConverter()
        return entities.map { converter.convert(it) }
    }

    /**
     * Saves the given [DialogConfig].
     * @param configuration Configuration to save.
     */
    fun save(configuration: DialogConfig) {
        val entity = DialogConverter().convert(configuration)
        dialogsDao.upsert(entity)
    }
}
