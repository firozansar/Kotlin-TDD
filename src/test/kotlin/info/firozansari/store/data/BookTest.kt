package info.firozansari.store.data

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BookTest {

    @Test
    fun books_are_not_equal_even_if_their_reference_is_equal() {
        val reference = "REF"
        val firstBook: Book = createBook(reference, "1")
        val secondBook: Book = createBook(reference, "2")
        assertFalse(firstBook == secondBook)
    }

    fun createBook(ref: String, uniqueSuffix: String): Book {
        return Book(ref, "title$uniqueSuffix", "desc$uniqueSuffix")
    }
}
