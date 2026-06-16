package org.iesra.app

import org.iesra.io.InputFileReader
import org.iesra.io.ScriptWriter
import org.iesra.service.PhotoProcessor
import org.iesra.service.ProcessingSummaryService
import java.nio.file.Path

class PhotoRenamerApp {

    fun run(inputPath: Path) {

        val fileReader = InputFileReader()
        val photosProcessor = PhotoProcessor()
        val writer = ScriptWriter()
        val console = ProcessingSummaryService()

        // Leer el fichero de entrada ya validado por ArgumentParser.
        // Validar el formato general del fichero y construir el objeto TripInput.

        val trip  = fileReader.leerArchivos(inputPath)

        // Procesar las fotos validas y generar el resultado con comandos y estadisticas.

        val proccesedPhotos = photosProcessor.process(trip)

        // Escribir el script <lugar>.sh con los comandos mv generados.

        writer.write(trip.place, proccesedPhotos.commands, inputPath)

        // Mostrar por consola el resumen de fotos leidas, correctas y erroneas.

        console.output(proccesedPhotos, trip)


    }
}
