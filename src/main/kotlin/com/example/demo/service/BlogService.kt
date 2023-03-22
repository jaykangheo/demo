package com.example.demo.service

import com.example.demo.dto.BlogTemplate
import org.springframework.data.domain.Pageable

interface BlogService {
    fun getBlog(query: String, pageable: Pageable): BlogTemplate
    fun save(documentTemplate: BlogTemplate.DocumentTemplate): BlogTemplate.DocumentTemplate
}
