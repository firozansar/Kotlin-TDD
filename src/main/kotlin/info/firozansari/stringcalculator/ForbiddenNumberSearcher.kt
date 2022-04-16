package info.firozansari.stringcalculator

/**
 * Interface created to represent a validator that evaluates a list of integers.
 */
internal interface ForbiddenNumberSearcher {
    @Throws(ValidatorException::class)
    fun validate(numbers: List<Int>)
}
