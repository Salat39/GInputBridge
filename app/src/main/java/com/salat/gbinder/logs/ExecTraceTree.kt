package com.salat.gbinder.logs

import timber.log.Timber

class ExecTraceTree : Timber.DebugTree() {
    private var stackTrace: StackTraceElement? = null

    override fun createStackElementTag(element: StackTraceElement): String? {
        stackTrace = element
        return super.createStackElementTag(element)
    }

    override fun log(priority: Int, t: Throwable?) {
        if (t?.message != null) {
            super.log(priority, Throwable(t.message?.withTrace(priority)))
        } else {
            super.log(priority, t)
        }
    }

    override fun log(priority: Int, message: String?, vararg args: Any?) {
        super.log(priority, message?.withTrace(priority), *args)
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, message.withTrace(priority), t)
    }

    override fun log(priority: Int, t: Throwable?, message: String?, vararg args: Any?) {
        super.log(priority, t, message?.withTrace(priority), *args)
    }

    private fun String.withTrace(priority: Int): String = if (stackTrace != null) {
        val trace =
            "${stackTrace?.methodName?.withoutLambda()} (${stackTrace?.fileName}:${stackTrace?.lineNumber})"
        stackTrace = null
        val emoji = when (priority) {
            1, 2 -> "\uD83D\uDCAC"
            3 -> "ℹ\uFE0F"
            4, 5 -> "⚠\uFE0F"
            else -> "\uD83D\uDEA9"
        }
        "$this\n$emoji -> $trace"
    } else {
        this
    }

    private fun String.withoutLambda(): String = if (!contains("$")) {
        this
    } else {
        split("$").first()
    }
}
