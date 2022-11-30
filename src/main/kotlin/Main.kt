import kotlin.math.PI
import kotlin.math.sqrt

fun main() {
    val squareCabin = SquareCabin(6, 50.0)
    val roundHut = RoundHut(3, 10.0)
    val roundTower = RoundTower(4, 15.5)

    with(squareCabin){
        println("\nSquare Cabin\n============")
        println("Capacity: $capacity")
        println("Material: $buildingMaterial")
        println("Has room? ${hasRoom()}")
        println("Floor area: %.2f".format(floorArea()))
    }

    with(roundHut){
        println("\nRound Hut\n============")
        println("Material: $buildingMaterial")
        println("Capacity: $capacity")
        println("Floor area: %.2f".format(floorArea()))
        println("Carpet Length: %.2f".format(calculateMaxCarpetLength()))

        println("Has room? ${hasRoom()}")
        getRoom()
        println("Has room? ${hasRoom()}")
        getRoom()
    }

    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: $buildingMaterial")
        println("Capacity: $capacity")
        println("Has room? ${hasRoom()}")
        println("Floor area: %.2f".format(floorArea()))
        println("Carpet Length: %.2f".format(calculateMaxCarpetLength()))

    }

}

//The blueprint of the subclasses
abstract class Dwelling(private var residents: Int) {
    abstract val buildingMaterial: String
    abstract val capacity: Int

    fun hasRoom(): Boolean {
        return residents < capacity
    }

    abstract fun floorArea(): Double

    fun getRoom() {
        if(hasRoom()){
            residents++
            println("You got a room!\n\n")
        }
        else {
            println("Sorry, at capacity and no rooms left...\n")
        }
    }
}

class SquareCabin(
    residents: Int,
    private val length: Double) : Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6

    override fun floorArea(): Double {
        return length * length
    }
}

open class RoundHut(
    residents: Int,
    private val radius: Double) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4

    override fun floorArea(): Double {
        return PI * radius * radius
    }

    fun calculateMaxCarpetLength(): Double {
        return sqrt(2.0) * radius
    }
}

class RoundTower(
    residents: Int,
    radius: Double,
    private val floors: Int = 2) : RoundHut(residents, radius) {
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors

    override fun floorArea(): Double {
        return super.floorArea() * floors
    }
}