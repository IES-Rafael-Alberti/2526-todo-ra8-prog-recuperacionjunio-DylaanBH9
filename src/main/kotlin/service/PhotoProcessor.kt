package org.iesra.service

import org.iesra.model.PhotoFile
import org.iesra.model.PhotoTypeStats
import org.iesra.model.ProcessingResult
import org.iesra.model.ReflexPhotoFile
import org.iesra.model.RenameCommand
import org.iesra.model.SmartphonePhotoFile
import org.iesra.model.TripInput

class PhotoProcessor {
    fun process(input: TripInput): ProcessingResult {
        val newSmartphoneFiles = buildValidSmartphonePhotos(input.smartphoneFiles)
        val newReflexFiles = buildValidReflexPhotos(input.reflexFiles)

        val listPhotos = newReflexFiles + newSmartphoneFiles



        val commandList = mutableListOf<String>()

        var totalCorrectPhotos = 0
        var readSmartphonePhotos = 0
        var errorSmartphonePhotos = 0
        var readReflexPhotos = 0
        var errorReflexPhotos = 0

        val errorList = mutableMapOf<String, String>()

        try {
            val orderedPhotos = listPhotos.sortedBy { it.orderkey }
            for (photo in orderedPhotos) {
                when (photo) {
                    is SmartphonePhotoFile -> {readSmartphonePhotos++}
                    is ReflexPhotoFile -> {readReflexPhotos++}
                }
                try {
                    val target = "${input.place}_${String.format("%03d", contador)}"
                    val renameCommands = RenameCommand(photo.originalName, target)

                    val command = renameCommands.toScriptLine()

                    commandList.add(command)

                    contador++

                } catch (e: Exception) {

                    when (photo) {
                        is SmartphonePhotoFile -> {errorSmartphonePhotos++}
                        is ReflexPhotoFile -> {errorReflexPhotos++}
                    }


                }
                totalCorrectPhotos++
            }
        } catch (e: Exception) {

        }


        val smartPhoneStats = PhotoTypeStats(0,0,errorSmartphonePhotos)
        val reflexStats = PhotoTypeStats(0,0,errorReflexPhotos)

        return ProcessingResult(commandList, reflexStats, smartPhoneStats, totalCorrectPhotos)
    }

    private fun buildValidSmartphonePhotos(names: List<String>) : MutableList<SmartphonePhotoFile> {
        val listSmartphones = mutableListOf<SmartphonePhotoFile>()
        for (photo in names) {
            listSmartphones.add(SmartphonePhotoFile(photo))
        }
        return listSmartphones
    }

    private fun buildValidReflexPhotos(names: List<String>) : List<ReflexPhotoFile> {
        val listReflexs = mutableListOf<ReflexPhotoFile>()
        for (photo in names) {
            listReflexs.add(ReflexPhotoFile(photo))
        }
        return listReflexs
    }

    companion object {
        var contador = 0
    }
}