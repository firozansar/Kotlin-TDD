package info.firozansari.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class InfiniteBoardTest {

    @Nested
    inner class Equality {

        @Test
        fun `an empty board is the same as any-sized board with all cells dead`() {
            assertEquals(
                Infinite2DBoard(),
                boardFromString(
                    ".....",
                    ".....",
                    ".....",
                    ".....",
                )
            )
        }

        @Test
        fun `equality is defined by living cell coordinates`() {
            val board = Infinite2DBoard(
                0 to 0,
                1 to 1
            )

            assertAll({
                assertEquals(board, boardFromString(
                        "#.",
                        ".#",
                    )
                )
            }, {
                assertEquals(board, boardFromString(
                        "#....",
                        ".#...",
                        ".....",
                        ".....",
                    )
                )
            })
        }
    }

    @Nested
    inner class Evolution {

        @Test
        fun `board does not evolve when all cells survive and none are reborn`() {
            val board = Infinite2DBoard(
                1 to 1
            )
            assertEquals(
                board.evolve(
                    ClassicRule(survivalPredicate = { true }, birthPredicate = { false })
                ),
                board
            )
        }

        @Test
        fun `board dies when no cells survive and none are reborn`() {
            val board = Infinite2DBoard(
                1 to 1
            )
            assertEquals(
                board.evolve(
                    ClassicRule(survivalPredicate = { false }, birthPredicate = { false })
                ), Infinite2DBoard()
            )
        }

        @Test
        fun `board populates by dead neighbors next to a living cell are being reborn`() {
            val board = Infinite2DBoard(
                1 to 1
            )
            assertEquals(
                board.evolve(
                    ClassicRule(survivalPredicate = { false }, birthPredicate = { true })
                ),
                boardFromString(
                    "###",
                    "#.#",
                    "###"
                )
            )
        }
    }

    @Nested
    inner class Coordinates {

        @Test
        fun `adjacent coordinates are the eight neighbors of a coordinate`() {
            assertThat(
                (1 to 1).adjacent()
            ).containsExactlyInAnyOrder(
                0 to 0,
                0 to 1,
                0 to 2,
                1 to 0,
                1 to 2,
                2 to 0,
                2 to 1,
                2 to 2
            )
        }
    }

    companion object {
        fun boardFromString(vararg cellRows: String) = Infinite2DBoard(
            cellRows.flatMapIndexed { rowIndex, row ->
                row.withIndex()
                    .filterNot { it.value in setOf(' ', '.') }
                    .map { rowIndex to it.index }
            }
        )
    }
}