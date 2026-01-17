package info.firozansari.phone_system.system.impl

import info.firozansari.phone_system.DefaultPhone
import info.firozansari.phone_system.Phone
import info.firozansari.phone_system.system.*
import java.util.function.Consumer
import java.util.function.Function

class DefaultPhoneSystem @JvmOverloads constructor(factory: Function<in PhoneSocket, out ConnectedPhone> = Function<PhoneSocket, ConnectedPhone> { DefaultPhone() }) :
    PhoneSystem {
    private val phones: MutableMap<String, ConnectedPhone> = HashMap<String, ConnectedPhone>()
    private lateinit var factory: Function<in PhoneSocket?, out ConnectedPhone?>
    override fun getPhone(number: String?): Phone? {
        return internalGetPhone(number!!)
    }

    protected fun internalGetPhone(number: String): ConnectedPhone {
        return phones.computeIfAbsent(
            number,
            Function<String, ConnectedPhone?> { number: String -> newPhone(number) } as Function<in String, out ConnectedPhone>)
    }

    protected fun newPhone(number: String): ConnectedPhone? {
        return factory.apply(DefaultSocket(number))
    }

    protected inner class DefaultSocket(override val number: String) : PhoneSocket {
        override fun call(
            number: String,
            onReject: Consumer<RejectReason>,
            onAccept: Consumer<Call?>,
            onMessageReceive: Consumer<String>,
            onEnd: Runnable
        ): CallOutgoing {
            val request = CallRequest(number, onReject, onAccept, onMessageReceive, onEnd)
            request.sendToPhone(internalGetPhone(number))
            return request
        }

        override fun toString(): String {
            return "Socket[$number]"
        }
    }

    init {
        //this.factory = factory
    }
}