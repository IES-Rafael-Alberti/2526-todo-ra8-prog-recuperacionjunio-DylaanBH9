package org.iesra.service

import org.iesra.model.PhotoTypeStats
import org.iesra.model.ProcessingResult
import org.iesra.model.ReflexPhotoFile
import org.iesra.model.RenameCommand
import org.iesra.model.SmartphonePhotoFile
import org.iesra.model.TripInput

class PhotoProcessor {
    fun process(input: TripInput): ProcessingResult {
        var contador = 0
        val errorList = mutableMapOf<String, String>()
        val newSmartphoneFiles = buildValidSmartphonePhotos(input.smartphoneFiles, errorList)
        val newReflexFiles = buildValidReflexPhotos(input.reflexFiles, errorList)
        val listPhotos = newReflexFiles + newSmartphoneFiles



        val commandList = mutableListOf<String>()

        var totalCorrectPhotos = 0


            val orderedPhotos = listPhotos.sortedBy { it.orderkey }
            for (photo in orderedPhotos) {
                val target = "${input.place}_${String.format("%03d", contador)}"
                val renameCommands = RenameCommand(photo.originalName, target)

                val command = renameCommands.toScriptLine()

                commandList.add(command)

                contador++

                totalCorrectPhotos++
            }


        val smartPhoneStats = PhotoTypeStats(input.smartphoneFiles.size, newSmartphoneFiles.size, input.smartphoneFiles.size-newSmartphoneFiles.size)
        val reflexStats = PhotoTypeStats(input.reflexFiles.size,newReflexFiles.size,input.smartphoneFiles.size-newReflexFiles.size)

        return ProcessingResult(commandList, reflexStats, smartPhoneStats, totalCorrectPhotos, errorList)
    }

    private fun buildValidSmartphonePhotos(
        names: List<String>,
        errorList: MutableMap<String, String>
    ) : MutableList<SmartphonePhotoFile> {
        val listSmartphones = mutableListOf<SmartphonePhotoFile>()
        for (photo in names) {
            try {
                val objectPhoto = SmartphonePhotoFile(photo)
                objectPhoto.orderkey
                listSmartphones.add(SmartphonePhotoFile(photo))
            } catch (e: Exception) {
                errorList[photo] = e.message as String
            }
        }
        return listSmartphones
    }

    private fun buildValidReflexPhotos(
        names: List<String>,
        errorList: MutableMap<String, String>
    ) : List<ReflexPhotoFile> {
        val listReflexs = mutableListOf<ReflexPhotoFile>()
        for (photo in names) {
            try {
                val objectPhoto = ReflexPhotoFile(photo)
                objectPhoto.orderkey
                listReflexs.add(ReflexPhotoFile(photo))
            } catch (e: Exception) {
                errorList[photo] = e.message as String
            }
        }
        return listReflexs
    }

}