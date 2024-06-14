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
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [DialogTypeConverter].
 */
class DialogTypeConverterTest {
    private lateinit var converter: DialogTypeConverter

    @Before
    fun setup() {
        converter = DialogTypeConverter()
    }

    /**
     * Tests if a [DialogConfig] is properly converted by [DialogTypeConverter.convert]
     * to an equivalent [DialogEntity].
     */
    @Test
    fun `Converts a config to an entity`() {
        val config = createConfig()
        val entity = converter.convert(config)

        assertThat(config.id, equalTo(entity.id))
        assertThat(config.title, equalTo(entity.title))
        assertThat(config.message, equalTo(entity.message))
        assertThat(config.positiveButtonText, equalTo(entity.positiveButtonText))
        assertThat(config.negativeButtonText, equalTo(entity.negativeButtonText))
        assertThat(config.neutralButtonText, equalTo(entity.neutralButtonText))
        assertThat(config.isCancelable, equalTo(entity.isCancelable))
        assertThat(config.isCancelableOnTouchOutside, equalTo(entity.isCancelableOnTouchOutside))
        assertThat(config.isLinkable, equalTo(entity.isLinkable))
    }

    /**
     * Tests if a [DialogEntity] is properly converted by [DialogTypeConverter.convert]
     * to an equivalent [DialogConfig].
     */
    @Test
    fun `Converts an entity to a config`() {
        val entity = createEntity()
        val config = converter.convert(entity)

        assertThat(entity.id, equalTo(config.id))
        assertThat(entity.title, equalTo(config.title))
        assertThat(entity.message, equalTo(config.message))
        assertThat(entity.positiveButtonText, equalTo(config.positiveButtonText))
        assertThat(entity.negativeButtonText, equalTo(config.negativeButtonText))
        assertThat(entity.neutralButtonText, equalTo(config.neutralButtonText))
        assertThat(entity.isCancelable, equalTo(config.isCancelable))
        assertThat(entity.isCancelableOnTouchOutside, equalTo(config.isCancelableOnTouchOutside))
        assertThat(entity.isLinkable, equalTo(config.isLinkable))
    }

    /**
     * Creates a simple [DialogConfig] for use in tests.
     * @return Test config.
     */
    private fun createConfig() =
        DialogConfig(
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
