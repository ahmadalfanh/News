package com.alfan.jakartapostapplication.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.alfan.jakartapostapplication.data.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ArticleRepository @Inject constructor(private val apiService: ApiService) {
    fun getArticles() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArticlesPagingSource(apiService, null) }
        ).liveData
}