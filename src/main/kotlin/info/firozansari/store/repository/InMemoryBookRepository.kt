package info.firozansari.store.repository

import info.firozansari.store.data.Book

open class InMemoryBookRepository : BookRepository {

    private val hash: MutableMap<String?, Book> = HashMap()

    fun add(book: Book) {
        hash[getBookKey(book)] = book
    }

    override fun contains(reference: String?): Boolean {
        return hash.containsKey(reference)
    }

    override fun retrieveBook(reference: String): Book? {
        return hash[reference]
    }

    //~~~ Private helpers
    protected fun getBookKey(book: Book): String? {
        return book.reference
    }
}