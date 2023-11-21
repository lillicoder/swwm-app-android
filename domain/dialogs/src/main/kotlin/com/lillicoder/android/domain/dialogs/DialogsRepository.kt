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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Repository for dialog configurations.
 * @param context [Context] to access [DialogsDao] with.
 * @param dialogsDao [DialogsDao] that handles CRUD operations for dialog data.
 * @param dispatcher [CoroutineDispatcher] for background work.
 */
class DialogsRepository(
    context: Context,
    private val dialogsDao: DialogsDao = DialogsDatabase.getInstance(context).dialogsDao(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    // TODO What is the perf impact of the transform here?
    /**
     * Gets all known [DialogConfig] as a [Flow].
     */
    val configurations: Flow<List<DialogConfig>> = dialogsDao.dialogs().map {
        it.map { entity ->
            DialogTypeConverter().convert(entity)
        }
    }

    /**
     * Deletes the given [DialogConfig] from this repository.
     */
    suspend fun delete(configuration: DialogConfig) {
        withContext(dispatcher) {
            val entity = DialogTypeConverter().convert(configuration)
            dialogsDao.delete(entity)
        }
    }

    /**
     * Saves the given [DialogConfig] to this repository.
     * @param configuration Configuration to save.
     */
    suspend fun save(configuration: DialogConfig) {
        withContext(dispatcher) {
            val entity = DialogTypeConverter().convert(configuration)
            dialogsDao.upsert(entity)
        }
    }
}
