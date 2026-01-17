package info.firozansari.phone_system.system.impl

import info.firozansari.phone_system.system.*
import java.util.function.Consumer

class CallRequest(
    private val number1: String,
    onReject: Consumer<RejectReason>,
    onAccept: Consumer<Call?>,
    onMessageReceive: Consumer<String>,
    onEnd: Runnable
) : CallOutgoing, CallIncoming {

    private val onReject1: Consumer<RejectReason>
    private val onAccept1: Consumer<Call?>
    private val onMessageReceive1: Consumer<String>
    private val onEnd1: Runnable
    private lateinit var targetPhone: ConnectedPhone
    private var cnn: Connection? = null
    private var canceled = false
    fun sendToPhone(targetPhone: ConnectedPhone) {
        this.targetPhone = targetPhone
        targetPhone.receive(this)
    }

    private fun assertUnanswered() {
        check(!canceled) { "canceled" }
        check(cnn == null) { "connected" }
    }

    override fun cancel() {
        assertUnanswered()
        targetPhone.canceled(this)
        canceled = true
    }

    override fun accept(onMessageReceive: Consumer<String>, onEnd: Runnable): Call? {
        assertUnanswered()
        cnn = Connection(number1, onMessageReceive1, onEnd1, targetPhone.number, onMessageReceive, onEnd)
        onAccept1.accept(cnn!!.call1())
        return cnn!!.call2()
    }

    override fun reject(reason: RejectReason) {
        assertUnanswered()
        canceled = true
        onReject1.accept(reason)
    }

    override fun toString(): String {
        return (if (canceled) "CANCELLED " else "Connecting ") +
                number1 + " -> " +
                targetPhone.number
    }

    init {
        onReject1 = onReject
        onAccept1 = onAccept
        onMessageReceive1 = onMessageReceive
        onEnd1 = onEnd
    }
}