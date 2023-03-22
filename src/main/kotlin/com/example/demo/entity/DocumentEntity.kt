package com.example.demo.entity

import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Indexed
@Table(name = "document")
class DocumentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "title")
    @Field
    var title: String,

    @Column(name = "contents")
    @Field
    var contents: String,

    @Column(name = "url")
    @Field
    var url: String,

    @Column(name = "blog_name")
    @Field
    var blogName: String,

    @Column(name = "thumb_nail")
    @Field
    var thumbNail: String,

    @Column(name = "datetime")
    @Field
    var datetime: Long
)
