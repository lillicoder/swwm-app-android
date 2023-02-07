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

package com.lillicoder.app.log

/**
 * Interface for logging messages.
 */
interface Logger {

    /**
     * Describes the level of a log message.
     */
    enum class Level {
        DEBUG,
        ERROR,
        INFO,
        VERBOSE,
        WARN
    }

    /**
     * Logs a [Level.DEBUG] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     */
    fun d(tag: String?, message: String?) {
        d(tag, message, null)
    }

    /**
     * Logs a [Level.DEBUG] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     * @param throwable Exception to log.
     */
    fun d(tag: String?, message: String?, throwable: Throwable?)

    /**
     * Logs an [Level.ERROR] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     */
    fun e(tag: String?, message: String?) {
        e(tag, message, null)
    }

    /**
     * Logs an [Level.ERROR] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     * @param throwable Exception to log.
     */
    fun e(tag: String?, message: String?, throwable: Throwable?)

    /**
     * Logs an [Level.INFO] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     */
    fun i(tag: String?, message: String?) {
        i(tag, message, null)
    }

    /**
     * Logs an [[Level.INFO]] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     * @param throwable Exception to log.
     */
    fun i(tag: String?, message: String?, throwable: Throwable?)

    /**
     * Logs a [Level.VERBOSE] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     */
    fun v(tag: String?, message: String?) {
        v(tag, message, null)
    }

    /**
     * Logs a [Level.VERBOSE] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     * @param throwable Exception to log.
     */
    fun v(tag: String?, message: String?, throwable: Throwable?)

    /**
     * Logs a [Level.WARN] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     */
    fun w(tag: String?, message: String?) {
        w(tag, message, null)
    }

    /**
     * Logs a [Level.WARN] message.
     * @param tag Source of the log message.
     * @param throwable Exception to log.
     */
    fun w(tag: String?, throwable: Throwable?) {
        w(tag, null, throwable)
    }

    /**
     * Logs a [Level.WARN] message.
     * @param tag Source of the log message.
     * @param message Message to log.
     * @param throwable Exception to log.
     */
    fun w(tag: String?, message: String?, throwable: Throwable?)

    /**
     * Logs a What a Terrible Failure (WTF) message.
     * @param tag Source of the log message.
     * @param message Message to log.
     */
    fun wtf(tag: String?, message: String?) {
        wtf(tag, message, null)
    }

    /**
     * Logs a What a Terrible Failure (WTF) message.
     * @param tag Source of the log message.
     * @param throwable Exception to log.
     */
    fun wtf(tag: String?, throwable: Throwable?) {
        wtf(tag, null, throwable)
    }

    /**
     * Logs a What a Terrible Failure (WTF) message.
     * @param tag Source of the log message.
     * @param message Message to log.
     * @param throwable Exception to log.
     */
    fun wtf(tag: String?, message: String?, throwable: Throwable?)
}
