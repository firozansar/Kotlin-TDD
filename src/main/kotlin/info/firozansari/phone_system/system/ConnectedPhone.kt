package info.firozansari.phone_system.system

import info.firozansari.phone_system.Phone
/**
 * A phone that can receive call requests from a phone system.
 */
interface ConnectedPhone : Phone {
    fun receive(request: CallIncoming)
    fun canceled(request: CallIncoming)
}