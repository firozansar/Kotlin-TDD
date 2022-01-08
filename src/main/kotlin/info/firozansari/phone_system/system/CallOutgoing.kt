package info.firozansari.phone_system.system

/**
 * Allows to manage a call request that was send to another phone.
 */
interface CallOutgoing {
    /**
     * Cancels the call request.
     */
    fun cancel()
}