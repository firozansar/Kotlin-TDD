package info.firozansari.store

import info.firozansari.store.data.Book
import info.firozansari.store.exceptions.BookNotFoundException
import info.firozansari.store.exceptions.InvalidReferenceSyntaxException
import info.firozansari.store.repository.BookRepository
import info.firozansari.store.repository.ErrorCheckingBookRepository
import info.firozansari.store.repository.InMemoryBookRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class BookServiceImplTest {

    @Test
    @Throws(Exception::class)
    fun retrieval_should_obtain_dvd_from_repository() {
        val existingBook: Book = createSomeBook()
        val bookService: BookService = createServiceWithExistingBook(existingBook)
        val retrievedBook: Book? = existingBook.reference?.let { bookService.retrieveBook(it) }
        assertTrue(retrievedBook == existingBook)
    }

    @Test
    @Throws(Exception::class)
    fun summary_contains_dvd_reference() {
        val existingBook: Book = createSomeBook()
        val bookService: BookService = createServiceWithExistingBook(existingBook)
        val summary: String = existingBook.reference?.let { bookService.getBookSummary(it) } ?: ""
        assertNotNull(existingBook.reference)
        assertTrue(summary.contains(existingBook.reference!!))
    }

    @Test
    @Throws(Exception::class)
    fun summary_contains_dvd_title() {
        val existingBook: Book = createSomeBook()
        val bookService: BookService = createServiceWithExistingBook(existingBook)
        val summary: String = existingBook.reference?.let { bookService.getBookSummary(it) } ?: ""
        assertTrue(summary.contains(existingBook.title))
    }

    @Test
    @Throws(Exception::class)
    fun summary_contains_dvd_description() {
        val existingBook: Book = createSomeBook()
        val bookService: BookService = createServiceWithExistingBook(existingBook)
        val summary: String = existingBook.reference?.let { bookService.getBookSummary(it) } ?: ""
        assertTrue(summary.contains(existingBook.description))
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun dvd_is_retrieved_from_in_memory_storage() {
        val storedBook = Book("DVD-refs", "title", "description")
        val storageRepository = InMemoryBookRepository()
        storageRepository.add(storedBook)
        val errorCheckingRepository: BookRepository = ErrorCheckingBookRepository(storageRepository)
        val service: BookService = BookServiceImpl(errorCheckingRepository)
        val existingBook: Book? = storedBook.reference?.let { service.retrieveBook(it) }
        assertNotNull(existingBook)
        assertTrue(existingBook == storedBook)
    }

    //~~~~ Test helpers
    private fun createSomeBook(): Book {
        return Book("Book-Ref", "title", "description")
    }

    @Throws(InvalidReferenceSyntaxException::class, BookNotFoundException::class)
    private fun createServiceWithExistingBook(existingBook: Book): BookService {
//        val repository: BookRepository = mock(BookRepository::class.java)
//        `when`(repository.contains(existingDvd.getReference())).thenReturn(true)
//        `when`(repository.retrieveDvd(existingDvd.getReference())).thenReturn(existingDvd)
        return BookServiceImpl(null) // BookServiceImpl(repository)
    }
}