package info.firozansari.store.repository

import info.firozansari.store.data.Book
import info.firozansari.store.exceptions.BookNotFoundException
import info.firozansari.store.exceptions.InvalidReferenceSyntaxException

class ErrorCheckingBookRepository(private val decoratedRepository: BookRepository) : BookRepository {
    override fun contains(reference: String?): Boolean {
        return decoratedRepository.contains(reference)
    }

    @Throws(InvalidReferenceSyntaxException::class, BookNotFoundException::class)
    override fun retrieveBook(reference: String): Book? {
        return if (!reference.startsWith(REFERENCE_TAG)) {
            throw InvalidReferenceSyntaxException()
        } else {
            if (decoratedRepository.contains(reference)) {
                decoratedRepository.retrieveBook(reference)
            } else {
                throw BookNotFoundException()
            }
        }
    }

    companion object {
        const val REFERENCE_TAG = "BOOK"
    }
}