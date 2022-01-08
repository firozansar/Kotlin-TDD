package info.firozansari.gameoflife

import info.firozansari.gameoflife.replace.ReplacesWorld
import info.firozansari.gameoflife.values.MutableWorld
import info.firozansari.gameoflife.values.World
import org.junit.jupiter.api.Test

// Mockk
class SimulatesConwayTest {
    //@InjectMocks
    var subject: SimulatesConway? = null

    //@Mock
    var generatesSeedWorld: GeneratesSeedWorld? = null

    //@Mock
    var replacesWorld: ReplacesWorld? = null

    //@Mock
    var outputsWorld: OutputsWorld? = null
    @Test
    fun zeroGenerations() {
        val seedWorld: World = MutableWorld()
        //when(generatesSeedWorld.generate()).thenReturn(seedWorld);
        subject!!.simulate(0, 1337)

        //verify(outputsWorld).output(seedWorld);
    }

    @Test
    fun oneGeneration() {
        val seedWorld: World = MutableWorld()
        //when(generatesSeedWorld.generate()).thenReturn(seedWorld);
        val world2: World = MutableWorld()
        //when(replacesWorld.replace(seedWorld, 1337)).thenReturn(world2);
        subject!!.simulate(1, 1337)

        //verify(outputsWorld).output(seedWorld);
        //verify(outputsWorld).output(world2);
    }
}