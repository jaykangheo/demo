package com.example.demo.dto

data class SearchBlogResponse(
    val meta: Meta,
    val documents: List<Document>
) {

    data class Meta(
        val totalCount: Int,
        val pageableCount: Int,
        val isEnd: Boolean
    )

    data class Document(
        val title: String,
        val contents: String,
        val url: String,
        val blogName: String,
        val thumbNail: String,
        val dateTime: String
    )
}
