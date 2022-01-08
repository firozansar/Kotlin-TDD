package info.firozansari.phone_system.system.impl

import info.firozansari.phone_system.system.Call
import java.util.function.Consumer

internal class Connection(
    private val number1: String,
    private val onMessageReceive1: Consumer<String>,
    private val onEnd1: Runnable,
    private val number2: String,
    private val onMessageReceive2: Consumer<String>,
    private val onEnd2: Runnable
) {
    private var open = true
    fun cancel() {
        open = false
        onEnd2.run()
    }

    private fun assertOpen() {
        check(open) { "closed" }
    }

    private fun call(name: String, onMessageReceive: Consumer<String>, onEnd: Runnable): Call {
        return object : Call() {
            fun send(message: String) {
                assertOpen()
                onMessageReceive.accept(message)
            }

            fun end() {
                assertOpen()
                open = false
                onEnd.run()
            }

            override fun toString(): String {
                return (if (open) "Call " else "CLOSED ") + name
            }
        }
    }

    fun call1(): Call {
        return call("$number1 --> $number2", onMessageReceive2, onEnd2)
    }

    fun call2(): Call {
        return call("$number2 --> $number1", onMessageReceive1, onEnd1)
    }

    override fun toString(): String {
        return (if (open) "Call" else "CLOSED") + number1 + " <-> " + number2
    }
}