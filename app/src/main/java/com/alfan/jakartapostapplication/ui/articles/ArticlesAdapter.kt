package com.alfan.jakartapostapplication.ui.articles

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alfan.jakartapostapplication.R
import com.alfan.jakartapostapplication.data.model.Articles
import com.alfan.jakartapostapplication.data.model.Gallery
import com.alfan.jakartapostapplication.databinding.ItemArticlesBinding
import com.alfan.jakartapostapplication.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ArticlesAdapter(
     var context: Context,
) :
    PagingDataAdapter<Articles, ArticlesAdapter.ArticleViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ItemArticlesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class ArticleViewHolder(private val binding: ItemArticlesBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(articles: Articles) {
            val transformation =
                MultiTransformation(CenterCrop(), RoundedCorners(10))
            for (i in articles.gallery.indices) {
                val temp = articles.gallery[i]
                with(binding) {
                    Glide.with(itemView)
                        .load(temp.path_thumbnail)
                        .transform(transformation)
                        .placeholder(R.drawable.icon_jp)
                        .into(ivNews)
                    tvTitle.text = articles.title
                    tvPublishedDate.text = articles.published_date
                }
                binding.root.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("title", articles.title)
                    intent.putExtra("content", articles.summary)
                    intent.putExtra("published_date", articles.published_date)
                    intent.putExtra("image", temp.path_thumbnail)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }

            }


        }
    }

    interface OnItemClickListener {
        fun onItemClick(articles: Articles)
    }



    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Articles>() {
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean =
                oldItem == newItem
        }
    }
}