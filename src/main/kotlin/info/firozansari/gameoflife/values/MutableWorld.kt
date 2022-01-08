package info.firozansari.gameoflife.values

import info.firozansari.gameoflife.replace.*

class MutableWorld : World() {
    operator fun set(coordinates: Coordinates?, contents: Contents?) {
        // TODO
        //population[coordinates] = contents
    }

    override fun at(coordinates: Coordinates?): Contents? {
        return population[coordinates]
    }
}