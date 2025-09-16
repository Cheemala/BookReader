package com.cheemala.bookreader.model.data

data class BookVolumeInfo(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)