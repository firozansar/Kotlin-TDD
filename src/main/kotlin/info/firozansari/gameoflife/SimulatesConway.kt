package info.firozansari.gameoflife

import info.firozansari.gameoflife.replace.ReplacesWorld

// For a video overview of Game of Life TDD:
// https://www.youtube.com/watch?v=aeX5OXO-w30&list=PLIuJbrOVyGjl0keQ-QyiMEOCvmabJEf0t&index=2
// blog post: http://blog.testdouble.com/posts/2015-09-10-how-i-use-test-doubles.html
class SimulatesConway {
    var generatesSeedWorld: GeneratesSeedWorld? = null
    var replacesWorld: ReplacesWorld? = null
    var outputsWorld: OutputsWorld? = null
    fun simulate(generations: Int, timeLimit: Int) {
        var world = generatesSeedWorld?.generate()
        for (i in 0 until generations) {
            outputsWorld!!.output(world)
            world = replacesWorld!!.replace(world, timeLimit.toLong())
        }
        outputsWorld?.output(world)
    }
}