package org.iesra.model

class RenameCommand(val source: String, val target: String){
    fun toScriptLine(): String {
        val extension = source.split(".")[1]
        return "mv $source ${target}.$extension"
    }
}