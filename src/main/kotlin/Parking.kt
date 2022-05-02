class Parking(amountOfLots: Int) {
    private val parkingLots: MutableMap<Int, Car?> = (1..amountOfLots).associateWith { null }.toMutableMap()

    fun park(car: Car) =
        parkingLots.asSequence()
                .find { it.value == null }
                .takeIf { it.isNotNullWithMessageIfNull("Sorry, the parking lot is full.") }
                ?.let {
                    parkingLots[it.key] = car
                    println("${car.colour} car parked in spot ${it.key}.")
                }


    fun leave(lotNumber: Int) =
        lotNumber.let {
            parkingLots[it] = null
            println("Spot $lotNumber is free.")
        }


    fun status() =
        parkingLots.asSequence()
                .filter { it.value != null }
                .sortedBy { it.key }
                .ifEmpty {
                    println("Parking lot is empty.")
                    emptySequence()
                }
                .forEach { println("${it.key} ${it.value!!.licensePlate} ${it.value!!.colour}") }


    fun reset(amountOfLots: Int) =
        with(parkingLots) {
            clear()
            putAll((1..amountOfLots).associateWith { null }.toMutableMap())
            println("Created a parking lot with $amountOfLots spots.")
        }

    fun regByColor(color: String) =
        getSequenceByColor(color).joinToString { it.value!!.licensePlate }
                .takeIf { it.isNotBlank() }?.let { println(it) }

    fun spotByColor(color: String) =
        getSequenceByColor(color).joinToString { it.key.toString() }
                .takeIf { it.isNotBlank() }?.let { println(it) }

    fun spotByReg(licensePlate: String) =
        parkingLots.asSequence()
                .find { it.value?.licensePlate == licensePlate }
                .takeIf {
                    it.isNotNullWithMessageIfNull("No cars with registration number $licensePlate were found.")
                }?.let { println(it.key) }


    private fun getSequenceByColor(color: String) =
        parkingLots.asSequence()
                .filter { it.value?.colour?.equals(color, true) ?: false }
                .sortedBy { it.key }
                .ifEmpty {
                    println("No cars with color $color were found.")
                    emptySequence()
                }

    private fun Map.Entry<Int, Car?>?.isNotNullWithMessageIfNull(message: String): Boolean =
        if (this == null) {
            println(message)
            false
        } else true
}