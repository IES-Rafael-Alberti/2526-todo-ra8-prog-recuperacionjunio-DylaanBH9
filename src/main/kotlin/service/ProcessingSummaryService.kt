package org.iesra.service

import org.iesra.model.ProcessingResult
import org.iesra.model.TripInput

class ProcessingSummaryService {
    fun output(proccesedPhotos: ProcessingResult, trip: TripInput) {
        println("Procesadas las fotos de ${trip.place}:")
        if (proccesedPhotos.errorList.isEmpty()) {
            println("No se han detectado errores de formato.")
        } else {
            proccesedPhotos.errorList.forEach { file, error ->
                if (file[0] == 'P'){
                    println("Reflex: $file -> $error")
                } else {
                    println("SmartPhone: $file -> $error")
                }
            }
        }
        println("Réflex:")
        println("=======")
        println("Fotos leídas: ${proccesedPhotos.reflexStats.read}")
        println("Correctas: ${proccesedPhotos.reflexStats.correct}")
        println("Errores: ${proccesedPhotos.reflexStats.errors}")
        println("Smartphone: ")
        println("===========")
        println("Fotos leídas: ${proccesedPhotos.smartphoneStats.read}")
        println("Correctas: ${proccesedPhotos.smartphoneStats.correct}")
        println("Errores: ${proccesedPhotos.smartphoneStats.errors}")
        println("Generado el script ${trip.place}.sh con ${proccesedPhotos.totalCorrectPhotos} comandos mv.")
    }
}