package info.firozansari.fizzbuzz

class FizzBuzzConverter {
    fun convert(toConvertToFizzBuzz: Int): String {
        if (toConvertToFizzBuzz % 15 == 0) {
            return "FizzBuzz"
        }
        if (toConvertToFizzBuzz % 5 == 0) {
            return "Buzz"
        }
        return if (toConvertToFizzBuzz % 3 == 0) {
            "Fizz"
        } else toConvertToFizzBuzz.toString()
    }
}
