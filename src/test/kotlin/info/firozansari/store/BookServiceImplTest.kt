package info.firozansari.store

import info.firozansari.store.data.Book
import info.firozansari.store.exceptions.BookNotFoundException
import info.firozansari.store.exceptions.InvalidReferenceSyntaxException
import info.firozansari.store.repository.BookRepository
import info.firozansari.store.repository.ErrorCheckingBookRepository
import info.firozansari.store.repository.InMemoryBookRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class BookServiceImplTest {

    @MockK
    private lateinit var repository: BookRepository

    @Test
    fun retrieval_should_obtain_book_from_repository() {
        val existingBook: Book = createSomeBook()
        val bookService: BookService = createServiceWithExistingBook(existingBook)
        val retrievedBook: Book? = existingBook.reference?.let { bookService.retrieveBook(it) }
        assertTrue(retrievedBook == existingBook)
    }

    @Test
    fun summary_contains_book_reference() {
        val existingBook: Book = createSomeBook()
        val bookService: BookService = createServiceWithExistingBook(existingBook)
        val summary: String = existingBook.reference?.let { bookService.getBookSummary(it) } ?: ""
        assertNotNull(existingBook.reference)
        assertTrue(summary.contains(existingBook.reference!!))
    }

    @Test
    fun summary_contains_book_title() {
        val existingBook: Book = createSomeBook()
        val bookService: BookService = createServiceWithExistingBook(existingBook)
        val summary: String = existingBook.reference?.let { bookService.getBookSummary(it) } ?: ""
        assertTrue(summary.contains(existingBook.title))
    }

    @Test
    fun summary_contains_book_description() {
        val existingBook: Book = createSomeBook()
        val bookService: BookService = createServiceWithExistingBook(existingBook)
        val summary: String = existingBook.reference?.let { bookService.getBookSummary(it) } ?: ""
        assertTrue(summary.contains(existingBook.description))
    }

    @Test
    @Disabled
    fun book_is_retrieved_from_in_memory_storage() {
        val storedBook = Book("Book-Ref", "title", "description")
        val storageRepository = InMemoryBookRepository()
        storageRepository.add(storedBook)
        val errorCheckingRepository: BookRepository = ErrorCheckingBookRepository(storageRepository)
        val service: BookService = BookServiceImpl(errorCheckingRepository)
        val existingBook: Book? = storedBook.reference?.let { service.retrieveBook(it) }
        assertNotNull(existingBook)
        assertTrue(existingBook == storedBook)
    }

    private fun createServiceWithExistingBook(existingBook: Book): BookService {
        every { repository.contains(existingBook.reference) } returns true
        every { repository.retrieveBook(existingBook.reference!!) } returns existingBook
        return BookServiceImpl(repository)
    }

    private fun createSomeBook(): Book {
        return Book("Book-Ref", "title", "description")
    }
}
