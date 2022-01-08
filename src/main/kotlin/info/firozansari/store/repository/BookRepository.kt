package info.firozansari.store.repository

import info.firozansari.store.data.Book
import info.firozansari.store.exceptions.BookNotFoundException
import info.firozansari.store.exceptions.InvalidReferenceSyntaxException

interface BookRepository {
    operator fun contains(reference: String?): Boolean

    @Throws(InvalidReferenceSyntaxException::class, BookNotFoundException::class)
    fun retrieveBook(reference: String): Book?
}