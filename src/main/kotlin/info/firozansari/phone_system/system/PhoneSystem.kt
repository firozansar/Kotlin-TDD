package info.firozansari.phone_system.system

import info.firozansari.phone_system.Phone

/**
 * The public interface of a phone system.
 */
interface PhoneSystem {
    /**
     * Returns the phone for the given number.
     * @param number phone number
     * @return phone
     */
    fun getPhone(number: String?): Phone?
}