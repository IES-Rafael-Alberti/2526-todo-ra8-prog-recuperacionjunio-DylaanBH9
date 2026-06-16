package org.iesra.model

class RenameCommand(val source: String, val target: String){
    fun toScriptLine(): String {
        return "mv $source $target"
    }
}