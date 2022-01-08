package info.firozansari.gameoflife.replace.cell

import info.firozansari.gameoflife.replace.Coordinates
import info.firozansari.gameoflife.replace.Outcome
import info.firozansari.gameoflife.values.World

class ReplacesCell {
    var gathersNeighbors: GathersNeighbors? = null
    var determinesNextContents: DeterminesNextContents? = null
    fun replace(coordinates: Coordinates?, world: World?): Outcome {
        val neighbors = gathersNeighbors!!.gather(coordinates, world)
        val newContents = determinesNextContents!!.determine(world!!.at(coordinates), neighbors)
        return Outcome(newContents, neighbors)
    }
}