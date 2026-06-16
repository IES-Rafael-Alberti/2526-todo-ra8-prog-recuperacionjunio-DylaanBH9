package org.iesra.io

import org.iesra.model.TripInput
import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.readLines

class InputFileReader {
    fun leerArchivos(file: Path): TripInput {
        val lines = file.readLines()

        if (lines.size !in 3..3) {
            throw IllegalArgumentException("El fichero debe de contener solo 3 lineas de entrada")
        }

        val place = lines[0]
        val smartphonePhotos = lines[1].split(" ")
        val reflexPhotos = lines[2].split(" ")

        return TripInput(place, smartphonePhotos, reflexPhotos)
    }
}