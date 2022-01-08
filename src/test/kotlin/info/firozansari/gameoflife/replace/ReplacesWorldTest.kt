package info.firozansari.gameoflife.replace

import info.firozansari.gameoflife.replace.cell.ReplacesCell
import info.firozansari.gameoflife.values.MutableWorld
import info.firozansari.gameoflife.values.Point
import info.firozansari.gameoflife.values.World
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

//@RunWith(MockitoJUnitRunner.class)
class ReplacesWorldTest {
    //@InjectMocks
    var subject: ReplacesWorld? = null

    //@Mock
    var keepsTime: KeepsTime? = null

    //@Mock
    var replacesCell: ReplacesCell? = null
    @Test
    fun testForOneCellToBeReplaced() {
        //TimeLimit timeLimit = mock(TimeLimit.class);
        //when(timeLimit.timesUp()).thenReturn(false, true);
        //when(keepsTime.keep(42l)).thenReturn(timeLimit);
        val world1: World = MutableWorld()
        val nextContents = Contents()
        val outcome = Outcome(nextContents, ArrayList())
        //when(replacesCell.replace(new Coordinates(0,0), world1)).thenReturn(outcome);
        val result = subject!!.replace(world1, 42L)
        Assertions.assertEquals(result.at(Coordinates(0, 0)), nextContents)
    }

    @Test
    fun testForTwoCellsToBeReplaced() {
//		TimeLimit timeLimit = mock(TimeLimit.class);
//		when(timeLimit.timesUp()).thenReturn(false, false, true);
//		when(keepsTime.keep(42l)).thenReturn(timeLimit);
        val world1: World = MutableWorld()
        val nextContents = Contents()
        val neighbors: Collection<Point?> = Arrays.asList(Point(null, Coordinates(50, 50)))
        val outcome = Outcome(nextContents, neighbors)
        //when(replacesCell.replace(new Coordinates(0,0), world1)).thenReturn(outcome);
        val nextContents2 = Contents()
        val outcome2 = Outcome(nextContents2, ArrayList())
        //when(replacesCell.replace(new Coordinates(50,50), world1)).thenReturn(outcome2);
        val result = subject!!.replace(world1, 42L)
        Assertions.assertEquals(result.at(Coordinates(0, 0)), nextContents)
        Assertions.assertEquals(result.at(Coordinates(50, 50)), nextContents2)
    }
}