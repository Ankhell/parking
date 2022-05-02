fun main() = handleParkingCreation()?.let { handleInput(it) } ?: Unit

fun handleInput(parking: Parking) =
    with(InputReader()) {
        while (command != Command.EXIT) {
            when (command) {
                Command.CREATE -> parking.reset(firstParam.toInt())
                Command.PARK -> parking.park(Car(colour = secondParam, licensePlate = firstParam))
                Command.LEAVE -> parking.leave(firstParam.toInt())
                Command.STATUS -> parking.status()
                Command.REG_BY_COLOR -> parking.regByColor(firstParam)
                Command.SPOT_BY_COLOR -> parking.spotByColor(firstParam)
                Command.SPOT_BY_REG -> parking.spotByReg(firstParam)
                else -> println("Unknown/incorrect command!")
            }
            readNext()
        }
    }

fun handleParkingCreation(): Parking? =
    with(InputReader()) {
        while (command != Command.EXIT) {
            when (command) {
                Command.CREATE -> {
                    val parking = Parking(0)
                    parking.reset(firstParam.toInt())
                    return parking
                }
                else -> println("Sorry, a parking lot has not been created.")
            }
            readNext()
        }
        return null
    }
