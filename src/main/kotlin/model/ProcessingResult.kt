package org.iesra.model

data class ProcessingResult(
    val commands: List<String>,
    val reflexStats: PhotoTypeStats,
    val smartphoneStats: PhotoTypeStats,
    val totalCorrectPhotos: Int,
    val errorList: MutableMap<String, String>
)