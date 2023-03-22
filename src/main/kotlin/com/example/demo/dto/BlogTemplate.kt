package com.example.demo.dto

import java.time.Instant

data class BlogTemplate(
    val metaTemplate: MetaTemplate,
    val documentTemplates: List<DocumentTemplate>
) {
    data class MetaTemplate(
        val totalCount: Int,
        val pageableCount: Int,
        val isEnd: Boolean
    )

    data class DocumentTemplate(
        val title: String,
        val contents: String,
        val url: String,
        val blogName: String,
        val thumbNail: String,
        val datetime: Instant
    )
}
