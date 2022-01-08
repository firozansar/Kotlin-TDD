package info.firozansari.gameoflife.values

import info.firozansari.gameoflife.replace.Contents
import info.firozansari.gameoflife.replace.Coordinates

abstract class World {
    protected var population: Map<Coordinates, Contents> = HashMap()
    abstract fun at(coordinates: Coordinates?): Contents?
}