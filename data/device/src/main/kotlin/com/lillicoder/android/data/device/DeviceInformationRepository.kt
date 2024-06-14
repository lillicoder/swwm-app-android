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

package com.lillicoder.android.data.device

/**
 * Repository that can provide device information.
 */
class DeviceInformationRepository(
    private val provider: BuildInfoProvider = AndroidBuildInfoProvider(),
) {
    /**
     * Gets a list of [BuildInfo] about the current device hardware.
     * @return Hardware information.
     */
    fun hardware(): List<BuildInfo> {
        // Serial number omitted due to security restrictions;
        // partitions omitted due to Android Q SDK level requirement
        return listOf(
            provider.board(),
            provider.bootloader(),
            provider.brand(),
            provider.device(),
            provider.display(),
            provider.fingerprint(),
            provider.hardware(),
            provider.host(),
            provider.id(),
            provider.manufacturer(),
            provider.model(),
            provider.product(),
            provider.radioVersion(),
            provider.tags(),
            provider.time(),
            provider.type(),
            provider.user(),
        )
    }
}
