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

/**
 * Describes a provider of device hardware and build information.
 */
interface BuildInfoProvider {
    /**
     * Gets the name of the underlying board, like "goldfish".
     * @return Board info.
     */
    fun board(): BuildInfo

    /**
     * Gets the system bootloader version number.
     * @return Bootloader info.
     */
    fun bootloader(): BuildInfo

    /**
     * Gets the consumer-visible brand with which the product/hardware will be associated, if any.
     * @return Brand info.
     */
    fun brand(): BuildInfo

    /**
     * Gets the name of the industrial design.
     * @return Device info.
     */
    fun device(): BuildInfo

    /**
     * Gets the build ID string meant for displaying to the user.
     * @return Display info.
     */
    fun display(): BuildInfo

    /**
     * Gets the string that uniquely identifies this build.
     * @return Fingerprint info.
     */
    fun fingerprint(): BuildInfo

    /**
     * Gets the name of the hardware.
     * @return Hardware info.
     */
    fun hardware(): BuildInfo

    /**
     * Gets the host used for internal builds.
     * @return Host info.
     */
    fun host(): BuildInfo

    /**
     * Gets either a changelist number or an ID label.
     * @return ID info.
     */
    fun id(): BuildInfo

    /**
     * Gets the manufacturer of the product/hardware.
     * @return Manufacturer info.
     */
    fun manufacturer(): BuildInfo

    /**
     * Gets the end-user-visible name for the end product.
     * @return Model info.
     */
    fun model(): BuildInfo

    /**
     * Gets the name of the overall product.
     * @return Product info.
     */
    fun product(): BuildInfo

    /**
     * Gets the version string for the radio firmware.
     */
    fun radioVersion(): BuildInfo

    /**
     * Gets the comma-separated tags describing the build.
     * @return Tags info.
     */
    fun tags(): BuildInfo

    /**
     * Gets the time at which the build was produced.
     * @return Time info.
     */
    fun time(): BuildInfo

    /**
     * Gets the type of build.
     * @return Type info.
     */
    fun type(): BuildInfo

    /**
     * Gets the build user.
     * @return User info.
     */
    fun user(): BuildInfo
}
