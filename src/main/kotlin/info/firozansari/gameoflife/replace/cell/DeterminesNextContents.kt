package info.firozansari.gameoflife.replace.cell

import info.firozansari.gameoflife.replace.Contents
import info.firozansari.gameoflife.values.Cell
import info.firozansari.gameoflife.values.Nothing
import info.firozansari.gameoflife.values.Point

class DeterminesNextContents {
    fun determine(oldContents: Contents?, neighbors: Collection<Point?>?): Contents {
        return when (countLiveOnes(neighbors)) {
            2 -> if (oldContents is Cell) Cell() else Nothing()
            3 -> Cell()
            else -> Nothing()
        }
    }

    private fun countLiveOnes(neighbors: Collection<Point?>?): Int {
        var i = 0
        for (point in neighbors!!) {
            if (point!!.contents is Cell) {
                i++
            }
        }
        return i
    }
}