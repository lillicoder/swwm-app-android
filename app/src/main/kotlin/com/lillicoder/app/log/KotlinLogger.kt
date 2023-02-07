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
 * [Logger] for pure Kotlin classes. Safe for unit tests.
 */
class KotlinLogger : Logger {

    override fun d(tag: String?, message: String?, throwable: Throwable?) {
        log(Logger.Level.DEBUG, tag, message, throwable)
    }

    override fun e(tag: String?, message: String?, throwable: Throwable?) {
        log(Logger.Level.ERROR, tag, message, throwable)
    }

    override fun i(tag: String?, message: String?, throwable: Throwable?) {
        log(Logger.Level.INFO, tag, message, throwable)
    }

    override fun v(tag: String?, message: String?, throwable: Throwable?) {
        log(Logger.Level.VERBOSE, tag, message, throwable)
    }

    override fun w(tag: String?, message: String?, throwable: Throwable?) {
        log(Logger.Level.WARN, tag, message, throwable)
    }

    override fun wtf(tag: String?, message: String?, throwable: Throwable?) {
        log(Logger.Level.ERROR, tag, message, throwable)
    }

    /**
     * Logs the given message
     * @param level Log level.
     * @param tag Source of the log message.
     * @param message Message to log.
     * @param throwable Exception to log.
     */
    private fun log(level: Logger.Level, tag: String?, message: String?, throwable: Throwable?) {
        println("[Log] [$level.name] [$tag] $message")
        throwable?.printStackTrace()
    }
}
