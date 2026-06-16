package org.iesra.service

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

        for (photo in listPhotos) {
            val target = "${input.place}_${String.format("%03d", contador)}"
            val renameCommands = RenameCommand(photo.originalName, target)

            val command = renameCommands.toScriptLine()

            commandList.add(command)

            contador++
        }


        return ProcessingResult(commandList, )
    }

    private fun buildValidSmartphonePhotos(names: List<String>) : List<SmartphonePhotoFile> {
        val orderkey =
    }

    private fun buildValidReflexPhotos(names: List<String>) : List<ReflexPhotoFile> {

    }

    companion object {
        var contador = 0
    }
}