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

package com.lillicoder.android.data.device

import android.os.Build

// TODO Write an instrumented test for this since its publicly exposed

/**
 * [BuildInfoProvider] that uses values from the Android [Build] namespace.
 */
class AndroidBuildInfoProvider : BuildInfoProvider {
    override fun board() =
        BuildInfo(
            R.string.build_board,
            Build.BOARD,
        )

    override fun bootloader() =
        BuildInfo(
            R.string.build_bootloader,
            Build.BOOTLOADER,
        )

    override fun brand() =
        BuildInfo(
            R.string.build_brand,
            Build.BRAND,
        )

    override fun device() =
        BuildInfo(
            R.string.build_device,
            Build.DEVICE,
        )

    override fun display() =
        BuildInfo(
            R.string.build_display,
            Build.DISPLAY,
        )

    override fun fingerprint() =
        BuildInfo(
            R.string.build_fingerprint,
            Build.FINGERPRINT,
        )

    override fun hardware() =
        BuildInfo(
            R.string.build_hardware,
            Build.HARDWARE,
        )

    override fun host() =
        BuildInfo(
            R.string.build_host,
            Build.HOST,
        )

    override fun id() =
        BuildInfo(
            R.string.build_id,
            Build.ID,
        )

    override fun manufacturer() =
        BuildInfo(
            R.string.build_manufacturer,
            Build.MANUFACTURER,
        )

    override fun model() =
        BuildInfo(
            R.string.build_model,
            Build.MODEL,
        )

    override fun product() =
        BuildInfo(
            R.string.build_product,
            Build.PRODUCT,
        )

    override fun radioVersion() =
        BuildInfo(
            R.string.build_radio_version,
            Build.getRadioVersion() ?: Build.UNKNOWN,
        )

    override fun tags() =
        BuildInfo(
            R.string.build_tags,
            Build.TAGS,
        )

    override fun time() =
        BuildInfo(
            R.string.build_time,
            Build.TIME.toString(),
        )

    override fun type() =
        BuildInfo(
            R.string.build_type,
            Build.TYPE,
        )

    override fun user() =
        BuildInfo(
            R.string.build_user,
            Build.USER,
        )
}
