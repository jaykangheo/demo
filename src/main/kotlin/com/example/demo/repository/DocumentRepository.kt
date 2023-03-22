package com.example.demo.repository

import com.example.demo.entity.DocumentEntity
import org.springframework.data.domain.Pageable

interface DocumentRepository {
    fun search(query: String, pageable: Pageable?): List<DocumentEntity>
}
