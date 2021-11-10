package com.alfan.jakartapostapplication.data.respone

import com.alfan.jakartapostapplication.data.model.Articles

data class ArticlesResponse(
    val code: Int?,
    val text: Int?,
)
data class Data(
    val data: List<Articles>
)