package info.firozansari.phone_system.system

import java.util.function.Consumer

/**
 * Connects a phone to a phone system.
 */
interface PhoneSocket {
    /**
     * Returns the phone number.
     * @return phone number
     */
    val number: String

    /**
     * Sends a call request.
     * @param number target phone number
     * @param onReject rejection handler
     * @param onAccept call acceptance handler
     * @param onMessageReceive incoming message handler, if a call is established
     * @param onEnd call cancellation handler, if a call is established
     * @return new outgoing call
     */
    fun call(
        number: String,
        onReject: Consumer<RejectReason>,
        onAccept: Consumer<Call?>,
        onMessageReceive: Consumer<String>,
        onEnd: Runnable
    ): CallOutgoing
}