package com.alfan.jakartapostapplication.ui.articles

import android.view.AbsSavedState
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alfan.jakartapostapplication.data.ArticleRepository

class ArticlesViewModel @ViewModelInject constructor(
    private val repository: ArticleRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {
    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }
    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)
    val article = currentQuery.switchMap {
        repository.getArticles().cachedIn(viewModelScope)

    }

}