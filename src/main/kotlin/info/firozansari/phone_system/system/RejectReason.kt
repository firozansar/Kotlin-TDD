package info.firozansari.phone_system.system

/**
 * Reasons for rejecting a call.
 */
enum class RejectReason {
    /**
     * The phone line is busy.
     */
    BUSY,

    /**
     * The recipient declined the call.
     */
    DECLINED
}