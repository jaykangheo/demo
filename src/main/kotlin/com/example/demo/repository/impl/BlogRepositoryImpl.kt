package com.example.demo.repository.impl

import com.example.demo.entity.DocumentEntity
import com.example.demo.repository.DocumentRepository
import org.hibernate.search.jpa.Search
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.PersistenceContextType

@Repository
class BlogRepositoryImpl : DocumentRepository {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private val entityManager: EntityManager? = null

    override fun search(query: String, pageable: Pageable?): List<DocumentEntity> {
        val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)
        val queryBuilder = fullTextEntityManager.searchFactory.buildQueryBuilder()
            .forEntity(DocumentEntity::class.java)
            .get()

        val query = queryBuilder.simpleQueryString()
            .onFields("title", "contents", "blogName")
            .matching(query)
            .createQuery()
        val fullTextQuery = fullTextEntityManager.createFullTextQuery(query, DocumentEntity::class.java)
        return fullTextQuery.resultList.map { it as DocumentEntity }
    }
}
