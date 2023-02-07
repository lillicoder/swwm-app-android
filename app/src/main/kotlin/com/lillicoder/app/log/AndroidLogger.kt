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

import android.util.Log

/**
 * [Logger] for classes that use the Android SDK.
 */
class AndroidLogger : Logger {

    override fun d(tag: String?, message: String?, throwable: Throwable?) {
        Log.d(tag, message, throwable)
    }

    override fun e(tag: String?, message: String?, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }

    override fun i(tag: String?, message: String?, throwable: Throwable?) {
        Log.i(tag, message, throwable)
    }

    override fun v(tag: String?, message: String?, throwable: Throwable?) {
        Log.v(tag, message, throwable)
    }

    override fun w(tag: String?, message: String?, throwable: Throwable?) {
        Log.w(tag, message, throwable)
    }

    override fun wtf(tag: String?, message: String?, throwable: Throwable?) {
        Log.wtf(tag, message, throwable)
    }
}
