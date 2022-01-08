package info.firozansari.gameoflife.replace

class Coordinates(x: Int, y: Int) {
    var x: Int?
    var y: Int?

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + if (x == null) 0 else x.hashCode()
        result = prime * result + if (y == null) 0 else y.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other = obj as Coordinates
        if (x == null) {
            if (other.x != null) return false
        } else if (x != other.x) return false
        if (y == null) {
            if (other.y != null) return false
        } else if (y != other.y) return false
        return true
    }

    init {
        this.x = x
        this.y = y
    }
}