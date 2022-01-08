package info.firozansari.gameoflife.util

import java.util.function.Function
import java.util.function.Predicate
import java.util.stream.Collectors

object Functional {
    fun <T> filter(collection: Collection<T>, predicate: Predicate<in T>?): Collection<T> {
        return collection.stream().filter(predicate).collect(Collectors.toList())
    }

    fun <T, R> map(collection: Collection<T>, predicate: Function<in T, out R>?): Collection<R> {
        return collection.stream().map(predicate).collect(Collectors.toList())
    }

    fun <T> min(collection: Collection<T>, comparator: Comparator<in T>?): T {
        return collection.stream().min(comparator).get()
    }

    fun <T> max(collection: Collection<T>, comparator: Comparator<in T>?): T {
        return collection.stream().max(comparator).get()
    }
}