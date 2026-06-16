package org.iesra.model

class ReflexPhotoFile(originalName: String): PhotoFile(originalName) {
    override var orderkey: String = ""
        get() {
        val fisrtPart = originalName.split("_")[0]
        val secondPart = originalName.split("_")[1]
        val year = "20${fisrtPart.substring(5..6)}"
        val month = fisrtPart.substring(3..4)
        val day = fisrtPart.substring(1..2)
        val hour = secondPart.substring(0..1)
        val minute = secondPart.substring(2..3)
        val second = secondPart.substring(3..4)
        val orderkey = "${year}${month}${day}${hour}${minute}${second}"
            if (month.toInt() > 12) {
                throw IllegalArgumentException("Fecha invalida")
            }else if (day.toInt() > 31) {
                throw IllegalArgumentException("Fecha invalida")
            } else if (hour.toInt() > 24) {
                throw IllegalArgumentException("Hora invalida")
            } else if (minute.toInt() > 60) {
                throw IllegalArgumentException("Hora invalida")
            }

            return orderkey
    }
}