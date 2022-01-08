package info.firozansari.phone_system

import info.firozansari.phone_system.system.*
import java.util.function.Consumer

class DefaultPhone(socket: PhoneSocket) : ConnectedPhone {
    private val socket: PhoneSocket
    override var status = PhoneStatus.IDLE
    private var outgoing: CallOutgoing? = null
    private var incoming: CallIncoming? = null
    private var call: Call? = null
    private var lastMessage = ""
    override var number = ""
        get() = socket.number

    override fun dial(number: String) {
        outgoing = socket.call(
            number,
            { reason: RejectReason? -> onCallReject(reason) },
            { call: Call? -> onCallAccept(call) },
            { message: String -> onMessageReceive(message) },
            { onCallEnd() })
    }

    override fun pushGreen() {}
    override fun pushRed() {}
    override fun send(message: String) {}
    override fun receive(request: CallIncoming) {
        incoming = request
        call = incoming?.accept({ message: String -> onMessageReceive(message) }, { onCallEnd() })
    }

    override fun canceled(request: CallIncoming) {}
    protected fun onCallReject(reason: RejectReason?) {}
    protected fun onCallAccept(call: Call?) {}
    protected fun onMessageReceive(message: String) {
        lastMessage = message
    }

    protected fun onCallEnd() {}
    fun getLastMessage(): String {
        return lastMessage
    }

    override fun toString(): String {
        return "Phone[$number]"
    }

    init {
        this.socket = socket
    }
}