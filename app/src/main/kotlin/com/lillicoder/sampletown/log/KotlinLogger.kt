package com.lillicoder.sampletown.log

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