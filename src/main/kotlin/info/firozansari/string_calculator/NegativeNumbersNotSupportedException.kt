package info.firozansari.string_calculator

/**
 * Exception created to be thrown when the application is used with negative numbers.
 * This exception contains information about the negative numbers that throws the exception.
 */
class NegativeNumbersNotSupportedException(private val negativeNumbers: List<Int>) : ValidatorException() {

    override val message: String
        get() = exceptionName + negativeNumbers.toString()

    companion object {
        const val exceptionName = "Negative numbers not supported exception:"
    }
}
