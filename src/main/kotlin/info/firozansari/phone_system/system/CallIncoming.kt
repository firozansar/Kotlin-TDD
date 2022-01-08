package info.firozansari.phone_system.system

import java.util.function.Consumer

/**
 * An incoming call request.
 */
interface CallIncoming {
    /**
     * Accepts the request and creates a call.
     * @param onMessageReceive incoming message handler
     * @param onEnd call cancellation handler
     * @return new call.
     */
    fun accept(onMessageReceive: Consumer<String>, onEnd: Runnable): Call?

    /**
     * Rejects the call for a given reason.
     * @param reason the reason
     */
    fun reject(reason: RejectReason)
}