package org.iesra.io

import java.io.File
import java.nio.file.Path

class ScriptWriter {
    fun write(place: String, commands: List<String>, inputPath: Path){
        val archivo = File(inputPath.parent.toFile(), "${place}.sh")
        val contenido = StringBuilder()

        for (command in commands) {
            contenido.appendLine(command)
        }

        archivo.writeText(contenido.toString())
    }
}