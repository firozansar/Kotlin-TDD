package info.firozansari.store

import info.firozansari.store.data.Book
import info.firozansari.store.exceptions.BookNotFoundException
import info.firozansari.store.exceptions.InvalidReferenceSyntaxException

interface BookService {

    @Throws(InvalidReferenceSyntaxException::class, BookNotFoundException::class)
    fun retrieveBook(reference: String): Book?

    @Throws(InvalidReferenceSyntaxException::class, BookNotFoundException::class)
    fun getBookSummary(reference: String): String
}