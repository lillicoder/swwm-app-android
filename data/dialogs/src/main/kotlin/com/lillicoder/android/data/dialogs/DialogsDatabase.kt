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
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "dialogs.db"

@Database(
    entities = [
        DialogEntity::class,
    ],
    version = 1,
)
abstract class DialogsDatabase : RoomDatabase() {
    /**
     * Gets the [DialogsDao] for this database.
     * @return Dialogs DAO.
     */
    abstract fun dialogsDao(): DialogsDao

    companion object {
        /**
         * Needs to be:
         *
         * 1) Volatile for thread-safe visibility
         * 2) Double-locked so that different threads get the right instance on creation
         */

        @Volatile
        private var instance: DialogsDatabase? = null

        /**
         * Gets an instance of [DialogsDatabase] with the given [Context]..
         * @param context Database context.
         * @return Dialogs database.
         */
        fun getInstance(context: Context): DialogsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        /**
         * Builds a [DialogsDatabase] instance with the given [Context].
         * @param context Database context.
         * @return Dialogs database.
         */
        private fun buildDatabase(context: Context): DialogsDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                DialogsDatabase::class.java,
                DATABASE_NAME,
            ).build()
    }
}
