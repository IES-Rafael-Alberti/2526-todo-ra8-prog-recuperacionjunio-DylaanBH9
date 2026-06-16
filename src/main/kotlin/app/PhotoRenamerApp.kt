package org.iesra.app

import org.iesra.io.InputFileReader
import org.iesra.service.PhotoProcessor
import java.nio.file.Path

class PhotoRenamerApp {

    fun run(inputPath: Path) {

        val fileReader = InputFileReader()
        val photosProcessor = PhotoProcessor()
        // Leer el fichero de entrada ya validado por ArgumentParser.
        // Validar el formato general del fichero y construir el objeto TripInput.

        val trip  = fileReader.leerArchivos(inputPath)

        // Procesar las fotos validas y generar el resultado con comandos y estadisticas.

        val proccesedPhotos = photosProcessor.process(trip)



        // Escribir el script <lugar>.sh con los comandos mv generados.

        // Mostrar por consola el resumen de fotos leidas, correctas y erroneas.

        // Si aplica la ampliacion de base de datos, guardar el resumen del procesamiento.
    }
}
