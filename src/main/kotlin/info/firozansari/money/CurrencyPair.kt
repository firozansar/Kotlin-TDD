package info.firozansari.money

class CurrencyPair(private val from: String, private val to: String) {

    override fun equals(other: Any?): Boolean {
        require(other is CurrencyPair) { "other must be CurrencyPair." }
        return from == other.from && to == other.to
    }

    override fun hashCode(): Int = 0
}