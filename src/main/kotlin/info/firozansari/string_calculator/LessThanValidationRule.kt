package info.firozansari.string_calculator

import info.firozansari.string_calculator.NumberValidator.ValidationRule

/**
 * ValidationRule created to discard all the numbers greater than or equals to the value passed in the constructor.
 */
internal class LessThanValidationRule(private val value: Int) : ValidationRule {
    override fun isValid(number: Int?): Boolean {
        return number!! < value
    }
}
