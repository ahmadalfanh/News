package com.alfan.jakartapostapplication.data

import android.os.Handler
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alfan.jakartapostapplication.data.model.Articles
import com.alfan.jakartapostapplication.data.network.ApiService
import retrofit2.HttpException
import java.io.IOException
import kotlin.time.days

private const val STARTING_PAGE_INDEX = 1

class ArticlesPagingSource(
    private val apiService: ApiService,
    private val query: Int?
) : PagingSource<Int, Articles>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Articles> {

        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = if (query != null) apiService.getArticles(
                query,
                position
            ) else apiService.getArticles(1, position)
            val articles = response.data
            LoadResult.Page(
                data = articles,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (articles.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}