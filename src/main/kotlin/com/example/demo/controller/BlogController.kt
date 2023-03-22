package com.example.demo.controller

import com.example.demo.dto.SearchBlogResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
interface BlogController {

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/v2/search/blog"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun searchBlog(
        @RequestParam(value = "query", required = true) query: String,
        @RequestParam(value = "sort", required = false) sort: String?,
        @RequestParam(value = "page", required = false) page: Int?,
        @RequestParam(value = "size", required = false) size: Int?
    ): ResponseEntity<SearchBlogResponse>

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/v2/post/blog"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun create(
        @RequestParam(value = "title", required = true) title: String,
        @RequestParam(value = "contents", required = true) contents: String,
        @RequestParam(value = "url", required = true) url: String,
        @RequestParam(value = "blogName", required = true) blogName: String,
        @RequestParam(value = "thumbNail", required = true) thumbNail: String,
    ): ResponseEntity<SearchBlogResponse.Document>
}
