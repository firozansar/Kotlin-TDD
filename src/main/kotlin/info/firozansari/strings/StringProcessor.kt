package info.firozansari.strings

import kotlin.Throws
import info.firozansari.strings.EmptyStringException

class StringProcessor {
    @Throws(EmptyStringException::class)
    fun countVowels(word: String): Int {
        if (word.isEmpty()) {
            throw EmptyStringException()
        }
        val chars = word.toCharArray()
        var counter = 0
        val vowels = "aeiouAEIOU"
        for (i in chars.indices) {
            if (vowels.contains(chars[i].toString() + "")) {
                counter++
            }
        }
        return counter
    }
}