package com.alfan.jakartapostapplication.data.model

data class Articles(
    val id: String?,
    val published_date: String?,
    val location: String?,
    val title: String?,
    val path: String?,
    val summary: String?,
    val channels: Channels?,
    val tags: List<Tags>?,
    val gallery: List<Gallery>,
)

data class Channels(
    val id: Int?,
    val name: String?,
    val parent: String?,
    val color: String?,
)

data class Tags(
    val id: Int?,
    val name: String?,
    val link: String?,
)

data class Gallery(
    val id: Int?,
    val title: String?,
    val path_origin: String?,
    val path_thumbnail: String?,
    val path_small: String?,
    val path_medium: String?,
    val path_large: String?,
    val source: String?,
    val content: String?,
    val photographer: String?,
    val keyword: String?,
)