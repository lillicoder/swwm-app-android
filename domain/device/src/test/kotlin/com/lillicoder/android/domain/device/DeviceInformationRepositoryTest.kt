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

package com.lillicoder.android.domain.device

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [DeviceInformationRepository].
 */
class DeviceInformationRepositoryTest {
    private lateinit var repository: DeviceInformationRepository

    @Before
    fun setup() {
        repository = DeviceInformationRepository(FakeBuildInfoProvider())
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
                BuildInfo(1, "board"),
                BuildInfo(2, "bootloader"),
                BuildInfo(3, "brand"),
                BuildInfo(4, "device"),
                BuildInfo(5, "display"),
                BuildInfo(6, "fingerprint"),
                BuildInfo(7, "hardware"),
                BuildInfo(8, "host"),
                BuildInfo(9, "id"),
                BuildInfo(10, "manufacturer"),
                BuildInfo(11, "model"),
                BuildInfo(12, "product"),
                BuildInfo(13, "radioVersion"),
                BuildInfo(14, "tags"),
                BuildInfo(15, "time"),
                BuildInfo(16, "type"),
                BuildInfo(17, "user"),
            )
        val hardware = repository.hardware()
        assertThat(hardware, equalTo(expected))
    }
}

/**
 * Fake [BuildInfoProvider] for use with unit tests.
 */
class FakeBuildInfoProvider : BuildInfoProvider {
    override fun board() = BuildInfo(1, "board")

    override fun bootloader() = BuildInfo(2, "bootloader")

    override fun brand() = BuildInfo(3, "brand")

    override fun device() = BuildInfo(4, "device")

    override fun display() = BuildInfo(5, "display")

    override fun fingerprint() = BuildInfo(6, "fingerprint")

    override fun hardware() = BuildInfo(7, "hardware")

    override fun host() = BuildInfo(8, "host")

    override fun id() = BuildInfo(9, "id")

    override fun manufacturer() = BuildInfo(10, "manufacturer")

    override fun model() = BuildInfo(11, "model")

    override fun product() = BuildInfo(12, "product")

    override fun radioVersion() = BuildInfo(13, "radioVersion")

    override fun tags() = BuildInfo(14, "tags")

    override fun time() = BuildInfo(15, "time")

    override fun type() = BuildInfo(16, "type")

    override fun user() = BuildInfo(17, "user")
}
