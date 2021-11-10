package com.alfan.jakartapostapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alfan.jakartapostapplication.data.model.Articles
import com.alfan.jakartapostapplication.databinding.ActivityMainBinding
import com.alfan.jakartapostapplication.ui.articles.ArticleLoadStateAdapter
import com.alfan.jakartapostapplication.ui.articles.ArticlesAdapter
import com.alfan.jakartapostapplication.ui.articles.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<ArticlesViewModel>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val adapterArticles = ArticlesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.rvList.apply {
            binding.rvList.setHasFixedSize(true)
            binding.rvList.adapter = adapterArticles.withLoadStateHeaderAndFooter(
                header = ArticleLoadStateAdapter { adapterArticles.retry() },
                footer = ArticleLoadStateAdapter { adapterArticles.retry() }
            )
            binding.swipeRefresh.setOnRefreshListener {
                adapterArticles.retry()
                Handler().postDelayed(Runnable {
                    binding.swipeRefresh.isRefreshing = false
                }, 4000)
            }
        }
        binding.swipeRefresh.setOnRefreshListener {
            Handler().postDelayed(Runnable {
                adapterArticles.refresh()
                binding.swipeRefresh.isRefreshing = false
            }, 4000)
        }

        viewModel.article.observe(this) {
            adapterArticles.submitData(this.lifecycle, it)
        }

        adapterArticles.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvList.isVisible = loadState.source.refresh is LoadState.NotLoading
                tvFailed.isVisible = loadState.source.refresh is LoadState.Error

                //not found
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapterArticles.itemCount < 1
                ) {
                    rvList.isVisible = false
                    tvNotFound.isVisible = true
                } else {
                    tvNotFound.isVisible = false
                }
            }
        }


    }



}