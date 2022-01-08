package info.firozansari.phone_system.system.impl

import java.util.function.Consumer

object VoicemailHelper {
    private var actions: MutableList<Runnable> = ArrayList()

    /**
     * Executes an action after the voicemail timeout passed.
     * @param action to be executed
     */
    fun onTimeoutDo(action: Runnable) {
        actions.add(action)
    }

    /**
     * Executes all on-timeout actions.
     */
    fun waitUntilTimeout() {
        val next: List<Runnable> = actions
        reset()
        next.forEach(Consumer { obj: Runnable -> obj.run() })
    }

    fun reset() {
        actions = ArrayList()
    }
}