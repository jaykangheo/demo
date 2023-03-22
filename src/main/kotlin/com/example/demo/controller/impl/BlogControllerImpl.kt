package com.example.demo.controller.impl

import com.example.demo.controller.BlogController
import com.example.demo.dto.BlogTemplate
import com.example.demo.dto.SearchBlogResponse
import com.example.demo.service.BlogService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class BlogControllerImpl(
    private val service: BlogService
) : BlogController {

    override fun searchBlog(query: String, sort: String?, page: Int?, size: Int?): ResponseEntity<SearchBlogResponse> {
        val pageRequest: Pageable = PageRequest.of(page ?: 1, size ?: 10, convertSort(sort))
        val res = service.getBlog(query = query, pageable = pageRequest)

        val body = SearchBlogResponse(
            SearchBlogResponse.Meta(
                totalCount = res.metaTemplate.totalCount,
                pageableCount = res.metaTemplate.pageableCount,
                isEnd = res.metaTemplate.isEnd
            ),
            documents = res.documentTemplates.map { document ->
                SearchBlogResponse.Document(
                    title = document.title,
                    contents = document.contents,
                    url = document.url,
                    blogName = document.blogName,
                    thumbNail = document.thumbNail,
                    dateTime = document.datetime.toString(),
                )
            }
        )
        return ResponseEntity.ok(body)
    }

    override fun create(
        title: String,
        contents: String,
        url: String,
        blogName: String,
        thumbNail: String
    ): ResponseEntity<SearchBlogResponse.Document> {
        val template = BlogTemplate.DocumentTemplate(
            title = title,
            contents = contents,
            url = url,
            blogName = blogName,
            thumbNail = thumbNail,
            datetime = Instant.now()

        )
        val res = service.save(template)
        val body = SearchBlogResponse.Document(
            title = res.title,
            contents = res.contents,
            url = res.url,
            blogName = res.blogName,
            thumbNail = res.thumbNail,
            dateTime = res.datetime.toString()
        )
        return ResponseEntity.ok(body)
    }

    private fun convertSort(sort: String?): Sort {
        return when (sort) {
            "accuracy" -> Sort.by("accuracy").descending()
            "recency" -> Sort.by("recency").descending()
            else -> Sort.by("accuracy").descending()
        }
    }
}
