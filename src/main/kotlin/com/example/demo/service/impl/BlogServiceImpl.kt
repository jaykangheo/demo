package com.example.demo.service.impl

import com.example.demo.dto.BlogTemplate
import com.example.demo.entity.DocumentEntity
import com.example.demo.repository.DocumentJpaRepository
import com.example.demo.repository.DocumentRepository
import com.example.demo.service.BlogService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class BlogServiceImpl(
    private val blogRepository: DocumentRepository,
    private val blogJpaRepository: DocumentJpaRepository,
) : BlogService {
    override fun getBlog(query: String, pageable: Pageable): BlogTemplate {
        val blogEntities = blogRepository.search(query, pageable)

        return BlogTemplate(
            metaTemplate = BlogTemplate.MetaTemplate(
                totalCount = 0,
                pageableCount = 0,
                isEnd = true,
            ),
            documentTemplates = blogEntities.map { blogEntity: DocumentEntity -> blogEntity.convertToTemplate() }
        )
    }

    override fun save(documentTemplate: BlogTemplate.DocumentTemplate): BlogTemplate.DocumentTemplate {
        val entity = DocumentEntity(
            id = 0,
            title = documentTemplate.title,
            contents = documentTemplate.contents,
            url = documentTemplate.url,
            blogName = documentTemplate.blogName,
            thumbNail = documentTemplate.thumbNail,
            datetime = documentTemplate.datetime.toEpochMilli()
        )
        return blogJpaRepository.save(entity).convertToTemplate()
    }

    private fun DocumentEntity.convertToTemplate(): BlogTemplate.DocumentTemplate {
        return BlogTemplate.DocumentTemplate(
            title = this.title,
            contents = this.contents,
            url = this.url,
            blogName = this.blogName,
            thumbNail = this.thumbNail,
            datetime = Instant.ofEpochMilli(this.datetime),
        )
    }
}
