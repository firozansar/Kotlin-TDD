package info.firozansari.parking.exception

class ParkingLotException(var type: ExceptionType, message: String?) : Exception(message) {
    enum class ExceptionType {
        PARKING_LOT_FULL, ALREADY_PARKED, NOT_FOUND
    }
}