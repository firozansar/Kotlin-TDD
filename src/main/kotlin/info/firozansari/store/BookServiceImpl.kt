package info.firozansari.store

import info.firozansari.store.data.Book
import info.firozansari.store.exceptions.BookNotFoundException
import info.firozansari.store.exceptions.InvalidReferenceSyntaxException
import info.firozansari.store.repository.BookRepository

internal class BookServiceImpl(private val bookRepository: BookRepository) : BookService {
    @Throws(InvalidReferenceSyntaxException::class, BookNotFoundException::class)
    override fun retrieveBook(reference: String): Book? {
        return bookRepository.retrieveBook(reference)
    }

    @Throws(InvalidReferenceSyntaxException::class, BookNotFoundException::class)
    override fun getBookSummary(reference: String): String {
        val book = bookRepository.retrieveBook(reference)
        return "[" + book?.reference + "] " + book?.title + " - " + book?.description
    }
}