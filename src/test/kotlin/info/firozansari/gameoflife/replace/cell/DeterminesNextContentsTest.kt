package info.firozansari.gameoflife.replace.cell

import info.firozansari.gameoflife.values.Cell
import info.firozansari.gameoflife.values.Nothing
import info.firozansari.gameoflife.values.Point
import org.junit.jupiter.api.Test

class DeterminesNextContentsTest {
    var subject = DeterminesNextContents()
    @Test
    fun testLiveCellsSurvival() {
//		assertEquals(subject.determine(new Cell(), liveNeighbors(0)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Cell(), liveNeighbors(1)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Cell(), liveNeighbors(2)), instanceOf(Cell.class));
//		assertEquals(subject.determine(new Cell(), liveNeighbors(3)), instanceOf(Cell.class));
//		assertEquals(subject.determine(new Cell(), liveNeighbors(4)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Cell(), liveNeighbors(5)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Cell(), liveNeighbors(6)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Cell(), liveNeighbors(7)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Cell(), liveNeighbors(8)), instanceOf(Nothing.class));
    }

    @Test
    fun testDeadCellsUndeadedness() {
//		assertTrue(subject.determine(Nothing(), liveNeighbors(0)) is instanceof Nothing::class)
//		assertEquals(subject.determine(new Nothing(), liveNeighbors(1)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Nothing(), liveNeighbors(2)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Nothing(), liveNeighbors(3)), instanceOf(Cell.class));
//		assertEquals(subject.determine(new Nothing(), liveNeighbors(4)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Nothing(), liveNeighbors(5)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Nothing(), liveNeighbors(6)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Nothing(), liveNeighbors(7)), instanceOf(Nothing.class));
//		assertEquals(subject.determine(new Nothing(), liveNeighbors(8)), instanceOf(Nothing.class));
    }

    private fun liveNeighbors(number: Int): Collection<Point> {
        val neighbors: MutableList<Point> = ArrayList()
        for (i in 0..8) {
            neighbors.add(Point(if (i < number) Cell() else Nothing(), null))
        }
        return neighbors
    }
}