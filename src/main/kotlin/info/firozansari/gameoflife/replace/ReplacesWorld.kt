package info.firozansari.gameoflife.replace

import info.firozansari.gameoflife.replace.cell.ReplacesCell
import info.firozansari.gameoflife.values.MutableWorld
import info.firozansari.gameoflife.values.Point
import info.firozansari.gameoflife.values.World
import java.util.*

class ReplacesWorld {
    var keepsTime: KeepsTime? = null
    var replacesCell: ReplacesCell? = null
    fun replace(oldWorld: World?, timeLimitInMs: Long): World {
        val newWorld = MutableWorld()
        val timeLimit = keepsTime!!.keep(timeLimitInMs)
        val cellsToReplace: Queue<Point?> = LinkedList(Arrays.asList(Point(null, Coordinates(0, 0))))
        while (!cellsToReplace.isEmpty() && !timeLimit!!.timesUp()) {
            val coordinates = cellsToReplace.remove()!!.coordinates
            val outcome = replacesCell!!.replace(coordinates, oldWorld)
            newWorld[coordinates] = outcome!!.nextContents
            cellsToReplace.addAll(outcome.neighbors!!)
        }
        return newWorld
    }
}