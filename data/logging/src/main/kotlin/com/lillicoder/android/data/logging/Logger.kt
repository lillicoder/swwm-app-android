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

package com.lillicoder.android.data.logging

import io.github.oshai.kotlinlogging.KotlinLogging

/**
 * All-purpose logger that works in Android code and unit tests.
 * @param tag Log tag.
 */
class Logger(
    private val tag: String,
) {
    private val delegate = KotlinLogging.logger(tag)

    /**
     * Logs the given verbose message.
     * @param message Message to log.
     */
    fun verbose(message: String) = delegate.trace { message }

    /**
     * Logs the given debug message.
     * @param message Message to log.
     */
    fun debug(message: String) = delegate.debug { message }

    /**
     * Logs the given info message.
     * @param message Message to log.
     */
    fun info(message: String) = delegate.info { message }

    /**
     * Logs the given warning message.
     * @param message Message to log.
     * @param throwable Optional exception that caused this warning.
     */
    fun warn(
        message: String,
        throwable: Throwable? = null,
    ) = delegate.warn(throwable) { message }

    /**
     * Logs the given error message.
     * @param message Message to log.
     * @param throwable Optional exception that caused this error.
     */
    fun error(
        message: String,
        throwable: Throwable? = null,
    ) = delegate.error(throwable) { message }
}
