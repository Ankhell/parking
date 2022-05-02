class InputReader {
    var command: Command = Command.UNKNOWN
    var firstParam: String = ""
    var secondParam: String = ""

    init {
        readNext()
    }

    fun readNext() {
        val input = readln().split(" ")
        if (input.size > 1) firstParam = input[1]
        if (input.size > 2) secondParam = input[2]
        command = when (val parsedCmd =
            Command.values().firstOrNull { it.name.lowercase() == input[0] } ?: Command.UNKNOWN) {
            Command.CREATE -> if (firstParam.toIntOrNull() != null) parsedCmd else {
                println("[create] should have a second parameter(Parking size)!")
                Command.UNKNOWN
            }
            Command.PARK -> if (firstParam != "" && secondParam != "") parsedCmd else {
                println("[park] should have second(License plate number) and third(Color) parameters")
                Command.UNKNOWN
            }
            Command.LEAVE -> if (firstParam.toIntOrNull() != null) parsedCmd else {
                println("[leave] should have a second parameter(Lot to leave)!")
                Command.UNKNOWN
            }
            Command.REG_BY_COLOR, Command.SPOT_BY_COLOR -> if (firstParam != "") parsedCmd else {
                println("[${parsedCmd.name.lowercase()}] should have a second parameter(Car color)!")
                Command.UNKNOWN
            }
            Command.SPOT_BY_REG -> if (firstParam != "") parsedCmd else {
                println("[spot_by_reg] should have a second parameter(License plate number)!")
                Command.UNKNOWN
            }
            else -> parsedCmd
        }
    }
}