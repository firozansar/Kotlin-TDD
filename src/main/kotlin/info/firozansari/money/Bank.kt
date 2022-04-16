package info.firozansari.money

class Bank {
    private var rates = mutableMapOf<CurrencyPair, Int>()

    fun reduce(source: Expression, to: String): Money {
        return source.reduce(this, to)
    }

    fun addRate(from: String, to: String, rate: Int) {
        rates[CurrencyPair(from, to)] = rate
    }

    fun rate(from: String, to: String): Int {
        return if (from == to) {
            1
        } else {
            check(rates.containsKey(CurrencyPair(from, to)))
            rates.getValue(CurrencyPair(from, to))
        }
    }
}
