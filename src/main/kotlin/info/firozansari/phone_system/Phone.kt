package info.firozansari.phone_system

interface Phone {
    var number: String
    fun dial(number: String)
    fun pushGreen()
    fun pushRed()
    fun send(message: String)
    var status: PhoneStatus
}