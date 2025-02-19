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

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Integration tests for [DialogsDao] and [DialogsDatabase].
 */
@RunWith(AndroidJUnit4::class)
class DialogsPersistenceTest {
    private lateinit var db: DialogsDatabase
    private lateinit var dao: DialogsDao

    /**
     * Creates an in-memory database for this test.
     */
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DialogsDatabase::class.java).build()
        dao = db.dialogsDao()
    }

    /**
     * Closes this test's database.
     */
    @After
    fun closeDb() {
        db.close()
    }

    /**
     * Tests that the database is empty by default when invoking [DialogsDao.dialogs]
     * with no data saved.
     */
    @Test
    fun databaseIsEmptyByDefault() =
        runTest {
            // Query dialogs directly, confirm list is not null and is empty
            val dialogs = dao.dialogs().first()
            assertThat(dialogs.size, equalTo(0))
        }

    /**
     * Tests that the database properly deletes previously saved entities when
     * invoking [DialogsDao.delete].
     */
    @Test
    fun delete() =
        runTest {
            // Save an entity
            val entity = createEntity()
            dao.upsert(entity)

            // Confirm entity has saved
            val preDeleteDialogs = dao.dialogs().first()
            assertThat(preDeleteDialogs.size, equalTo(1))
            assertThat(preDeleteDialogs[0].id, equalTo(entity.id))

            // Delete entity and confirm there are no entries
            dao.delete(entity)
            val postDeleteDialogs = dao.dialogs().first()
            assertThat(postDeleteDialogs.size, equalTo(0))
        }

    /**
     * Tests that all fields are properly saved when invoking [DialogsDao.upsert].
     */
    @Test
    fun upsert() =
        runTest {
            // Save an entity
            val entity = createEntity()
            dao.upsert(entity)

            // Confirm entity has been saved
            val dialogs = dao.dialogs().first()
            assertThat(dialogs.size, equalTo(1))

            // Verify all saved fields match starting entity
            val dialog = dialogs[0]
            assertThat(dialog.id, equalTo(entity.id))
            assertThat(dialog.title, equalTo(entity.title))
            assertThat(dialog.message, equalTo(entity.message))
            assertThat(dialog.positiveButtonText, equalTo(entity.positiveButtonText))
            assertThat(dialog.negativeButtonText, equalTo(entity.negativeButtonText))
            assertThat(dialog.neutralButtonText, equalTo(entity.neutralButtonText))
            assertThat(dialog.isCancelable, equalTo(entity.isCancelable))
            assertThat(
                dialog.isCancelableOnTouchOutside,
                equalTo(entity.isCancelableOnTouchOutside),
            )
            assertThat(dialog.isLinkable, equalTo(entity.isLinkable))
        }

    /**
     * Creates a simple [DialogEntity] for use in tests.
     * @return Test entity.
     */
    private fun createEntity() =
        DialogEntity(
            id = 1,
            title = "Test",
            message = "Test dialog",
            positiveButtonText = "Test positive",
            negativeButtonText = "Test negative",
            neutralButtonText = "Test neutral",
            isCancelable = false,
            isCancelableOnTouchOutside = false,
            isLinkable = false,
        )
}
