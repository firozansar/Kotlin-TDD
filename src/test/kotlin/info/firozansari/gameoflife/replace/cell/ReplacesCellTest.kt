package info.firozansari.gameoflife.replace.cell

import info.firozansari.gameoflife.replace.Contents
import info.firozansari.gameoflife.replace.Coordinates
import info.firozansari.gameoflife.values.MutableWorld
import info.firozansari.gameoflife.values.Point
import org.junit.jupiter.api.Test

//@RunWith(MockitoJUnitRunner.class)
class ReplacesCellTest {
    //@InjectMocks
    var subject: ReplacesCell? = null

    //@Mock
    var gathersNeighbors: GathersNeighbors? = null

    //@Mock 
    var determinesNextContents: DeterminesNextContents? = null
    @Test
    fun test() {
        val world = MutableWorld()
        val oldContents = Contents()
        val coordinates = Coordinates(1337, 1337)
        world[coordinates] = oldContents
        val neighbors: Collection<Point> = ArrayList()
        //when(gathersNeighbors.gather(coordinates, world)).thenReturn(neighbors);
        val newContents = Contents()
        //when(determinesNextContents.determine(oldContents , neighbors)).thenReturn(newContents);
        val result = subject!!.replace(coordinates, world)

        //assertEquals(result.neighbors, neighbors);
        //assertEquals(result.nextContents, newContents);
    }
}