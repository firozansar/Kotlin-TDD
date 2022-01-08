package info.firozansari.phone_system

import info.firozansari.phone_system.PhoneStatus.*
import info.firozansari.phone_system.system.PhoneSystem
import info.firozansari.phone_system.system.impl.DefaultPhoneSystem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.print.attribute.standard.PrinterState.IDLE


class DefaultPhoneTest {
    private val ALICE = "555-ALICE"
    private val BOB = "555-THE-BOBSTER"
    private val CAROL = "555-CAROL"

    private val system: PhoneSystem = DefaultPhoneSystem()
    private val alice = system.getPhone(ALICE)
    private val bob = system.getPhone(BOB)
    private val carol = system.getPhone(CAROL)

    private fun given_call_between(p1: Phone?, p2: Phone?) {
        p1!!.dial(p2!!.number)
        p1.pushGreen()
        p2.pushGreen()
        assertEquals(p1.status, IN_CALL)
        assertEquals(p2.status, IN_CALL)
    }

    @Test
    fun new_phone_is_idle() {
        assertEquals(alice!!.status, IDLE)
    }

    @Test
    fun after_dial_push_green_to_call() {
        alice!!.dial(BOB)
        alice.pushGreen()
        assertEquals(alice.status, CALLING)
    }

    @Test
    fun with_incoming_is_ringing() {
        alice!!.dial(BOB)
        alice.pushGreen()
        assertEquals(bob!!.status, RINGING)
    }
}