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

import android.os.Build

/**
 * Repository that can provide device information.
 */
class DeviceInformationRepository {

    /**
     * Gets a list of [BuildInfo] about the current device hardware.
     * @return Hardware information.
     */
    fun hardware(): List<BuildInfo> {
        // Serial number omitted due to security restrictions;
        // partitions omitted due to Android Q SDK level requirement
        return listOf(
            BuildInfo(R.string.build_board, Build.BOARD),
            BuildInfo(R.string.build_bootloader, Build.BOOTLOADER),
            BuildInfo(R.string.build_brand, Build.BRAND),
            BuildInfo(R.string.build_device, Build.DEVICE),
            BuildInfo(R.string.build_display, Build.DISPLAY),
            BuildInfo(R.string.build_fingerprint, Build.FINGERPRINT),
            BuildInfo(R.string.build_hardware, Build.HARDWARE),
            BuildInfo(R.string.build_host, Build.HOST),
            BuildInfo(R.string.build_id, Build.ID),
            BuildInfo(R.string.build_manufacturer, Build.MANUFACTURER),
            BuildInfo(R.string.build_model, Build.MODEL),
            BuildInfo(R.string.build_product, Build.PRODUCT),
            BuildInfo(R.string.build_radio_version, Build.getRadioVersion() ?: "null"),
            BuildInfo(R.string.build_tags, Build.TAGS),
            BuildInfo(R.string.build_time, Build.TIME.toString()),
            BuildInfo(R.string.build_type, Build.TYPE),
            BuildInfo(R.string.build_unknown, Build.UNKNOWN),
            BuildInfo(R.string.build_user, Build.USER)
        )
    }
}
