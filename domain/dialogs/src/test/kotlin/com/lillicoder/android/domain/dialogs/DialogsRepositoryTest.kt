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

package com.lillicoder.android.domain.dialogs

import com.lillicoder.android.data.dialogs.DialogEntity
import com.lillicoder.android.data.dialogs.DialogsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class DialogsRepositoryTest {
    private lateinit var repository: DialogsRepository

    @Before
    fun setup() {
        val fakeDao = FakeDialogsDao()
        repository = DialogsRepository(dialogsDao = fakeDao)
    }

    @Test
    fun `Repository successfully deletes a saved config`() =
        runTest {
            val config = createConfig()
            repository.save(config)

            // Ensure data is present
            repository.dialogs.collect {
                assertThat(it.size, equalTo(1))
            }

            repository.delete(config)

            repository.dialogs.collect {
                assertThat(it.isEmpty(), equalTo(true))
            }
        }

    @Test
    fun `Repository successfully saves a config`() =
        runTest {
            val config = createConfig()
            repository.save(config)

            repository.dialogs.collect {
                assertThat(it.size, equalTo(1))
                assertThat(it[0], equalTo(config))
            }
        }

    /**
     * Creates a simple [Dialog] for use in tests.
     * @return Test config.
     */
    private fun createConfig() =
        Dialog(
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

    /**
     * Fake implementation of [DialogsDao] for testing.
     */
    private class FakeDialogsDao : DialogsDao {
        private val dialogs: MutableSet<DialogEntity> = mutableSetOf()

        override suspend fun delete(entity: DialogEntity) {
            dialogs.remove(entity)
        }

        override fun dialogs(): Flow<List<DialogEntity>> {
            return flow { emit(dialogs.toList()) }
        }

        override suspend fun upsert(dialog: DialogEntity) {
            dialogs.add(dialog)
        }
    }
}
