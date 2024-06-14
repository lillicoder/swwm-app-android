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

package com.lillicoder.android.data.dialogs

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Repository for dialogs.
 * @param dialogsDao [DialogsDao] that handles CRUD operations for dialog data.
 * @param dispatcher [CoroutineDispatcher] for background work.
 */
class DialogsRepository(
    private val dialogsDao: DialogsDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    /**
     * Instantiates this repository with the given [Context].
     * @param context Context to access the default [DialogsDao] with.
     */
    constructor(context: Context) : this(
        dialogsDao = DialogsDatabase.getInstance(context).dialogsDao(),
    )

    /**
     * Gets all known [Dialog] as a [Flow].
     */
    val dialogs: Flow<List<Dialog>> =
        dialogsDao.dialogs().map {
            // TODO What is the perf impact of the transform here?
            it.map { entity ->
                DialogTypeConverter().convert(entity)
            }
        }

    /**
     * Deletes the given [Dialog] from this repository.
     */
    suspend fun delete(dialog: Dialog) {
        withContext(dispatcher) {
            val entity = DialogTypeConverter().convert(dialog)
            dialogsDao.delete(entity)
        }
    }

    /**
     * Saves the given [Dialog] to this repository.
     * @param dialog Configuration to save.
     */
    suspend fun save(dialog: Dialog) {
        withContext(dispatcher) {
            val entity = DialogTypeConverter().convert(dialog)
            dialogsDao.upsert(entity)
        }
    }
}
