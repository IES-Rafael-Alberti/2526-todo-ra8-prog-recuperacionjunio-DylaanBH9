package org.iesra.model

class SmartphonePhotoFile(originalName: String): PhotoFile(originalName) {
    override var orderkey: String = ""
        get(){
        val fisrtPart = originalName.split("_")[1]
        val secondPart = originalName.split("_")[2]
        val year = fisrtPart.substring(0..3)
        val month = fisrtPart.substring(4..5)
        val day = fisrtPart.substring(6..7)
        val hour = secondPart.substring(0..1)
        val minute = secondPart.substring(2..3)
        val second = secondPart.substring(3..4)
        val orderkey = "${year}${month}${day}${hour}${minute}${second}"
            return orderkey
    }
}