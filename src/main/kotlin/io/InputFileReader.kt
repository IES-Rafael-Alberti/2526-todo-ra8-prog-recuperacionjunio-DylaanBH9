package org.iesra.io

import org.iesra.model.TripInput
import java.nio.file.Path
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.readLines

class InputFileReader {
    fun leerArchivos(file: Path): TripInput {
        val lines = file.readLines()

        require(lines.size == 3) {"El fichero debe tener 3 lineas"}

        val place = lines[0]
        val smartphonePhotos = lines[1].split(" ")
        val reflexPhotos = lines[2].split(" ")

        require(place.split(" ").size == 1) {"El nombre de la ciudad debes ser una sola palabra"}

        return TripInput(place, smartphonePhotos, reflexPhotos)
    }
}