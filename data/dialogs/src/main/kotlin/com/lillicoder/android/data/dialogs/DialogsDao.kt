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

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DialogsDao {
    /**
     * Deletes the given [DialogEntity] from this DAO.
     * @param entity Entity to delete.
     */
    @Delete
    suspend fun delete(entity: DialogEntity)

    /**
     * Gets all dialogs from this DAO.
     * @return All dialogs.
     */
    @Query("SELECT * FROM ${Tables.Dialogs.TABLE_NAME}")
    fun dialogs(): Flow<List<DialogEntity>>

    /**
     * Inserts or updates the given [DialogEntity].
     * @param dialog Dialog to upsert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(dialog: DialogEntity)
}
