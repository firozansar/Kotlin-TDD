package info.firozansari.phone_system.system

/**
 * A connection between to phones.
 */
interface Call {
    /**
     * Sends a message.
     * @param message text
     */
    fun send(message: String?)

    /**
     * Cancels the call.
     */
    fun end()
}