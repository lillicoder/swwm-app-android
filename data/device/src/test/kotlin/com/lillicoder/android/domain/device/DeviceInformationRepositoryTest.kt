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

package com.lillicoder.android.domain.device

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [DeviceInformationRepository].
 */
class DeviceInformationRepositoryTest {
    private lateinit var repository: com.lillicoder.android.data.device.DeviceInformationRepository

    @Before
    fun setup() {
        repository =
            com.lillicoder.android.data.device.DeviceInformationRepository(FakeBuildInfoProvider())
    }

    /**
     * Tests that the correct number of hardware info entries is
     * returned from [DeviceInformationRepository.hardware].
     */
    @Test
    fun `Hardware list has correct size`() {
        val hardware = repository.hardware()
        assertThat(hardware.size, equalTo(17))
    }

    /**
     * Tests that the correct order and values  of hardware info entries is
     * returned from [DeviceInformationRepository.hardware].
     */
    @Test
    fun `Hardware list has correct order and values for each field`() {
        val expected =
            listOf(
                com.lillicoder.android.data.device.BuildInfo(1, "board"),
                com.lillicoder.android.data.device.BuildInfo(2, "bootloader"),
                com.lillicoder.android.data.device.BuildInfo(3, "brand"),
                com.lillicoder.android.data.device.BuildInfo(4, "device"),
                com.lillicoder.android.data.device.BuildInfo(5, "display"),
                com.lillicoder.android.data.device.BuildInfo(6, "fingerprint"),
                com.lillicoder.android.data.device.BuildInfo(7, "hardware"),
                com.lillicoder.android.data.device.BuildInfo(8, "host"),
                com.lillicoder.android.data.device.BuildInfo(9, "id"),
                com.lillicoder.android.data.device.BuildInfo(10, "manufacturer"),
                com.lillicoder.android.data.device.BuildInfo(11, "model"),
                com.lillicoder.android.data.device.BuildInfo(12, "product"),
                com.lillicoder.android.data.device.BuildInfo(13, "radioVersion"),
                com.lillicoder.android.data.device.BuildInfo(14, "tags"),
                com.lillicoder.android.data.device.BuildInfo(15, "time"),
                com.lillicoder.android.data.device.BuildInfo(16, "type"),
                com.lillicoder.android.data.device.BuildInfo(17, "user"),
            )
        val hardware = repository.hardware()
        assertThat(hardware, equalTo(expected))
    }
}

/**
 * Fake [BuildInfoProvider] for use with unit tests.
 */
class FakeBuildInfoProvider : com.lillicoder.android.data.device.BuildInfoProvider {
    override fun board() = com.lillicoder.android.data.device.BuildInfo(1, "board")

    override fun bootloader() = com.lillicoder.android.data.device.BuildInfo(2, "bootloader")

    override fun brand() = com.lillicoder.android.data.device.BuildInfo(3, "brand")

    override fun device() = com.lillicoder.android.data.device.BuildInfo(4, "device")

    override fun display() = com.lillicoder.android.data.device.BuildInfo(5, "display")

    override fun fingerprint() = com.lillicoder.android.data.device.BuildInfo(6, "fingerprint")

    override fun hardware() = com.lillicoder.android.data.device.BuildInfo(7, "hardware")

    override fun host() = com.lillicoder.android.data.device.BuildInfo(8, "host")

    override fun id() = com.lillicoder.android.data.device.BuildInfo(9, "id")

    override fun manufacturer() = com.lillicoder.android.data.device.BuildInfo(10, "manufacturer")

    override fun model() = com.lillicoder.android.data.device.BuildInfo(11, "model")

    override fun product() = com.lillicoder.android.data.device.BuildInfo(12, "product")

    override fun radioVersion() = com.lillicoder.android.data.device.BuildInfo(13, "radioVersion")

    override fun tags() = com.lillicoder.android.data.device.BuildInfo(14, "tags")

    override fun time() = com.lillicoder.android.data.device.BuildInfo(15, "time")

    override fun type() = com.lillicoder.android.data.device.BuildInfo(16, "type")

    override fun user() = com.lillicoder.android.data.device.BuildInfo(17, "user")
}
