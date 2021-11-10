package com.alfan.jakartapostapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alfan.jakartapostapplication.R
import com.alfan.jakartapostapplication.databinding.ActivityDetailBinding
import com.alfan.jakartapostapplication.databinding.ActivityMainBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private var title: String? = null
    private var tgl: String? = null
    private var content: String? = null
    private var imageLink: String? = null
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         _binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.toolbar.setOnClickListener {
            onBackPressed()
        }
        title = intent.getStringExtra("title")
        tgl = intent.getStringExtra("published_date")
        content = intent.getStringExtra("content")
        imageLink = intent.getStringExtra("image")
        Glide.with(this)
            .load(intent.getStringExtra("image"))
            .into(binding.ivContentImage)

        binding.tvTitleNews.text = title
        binding.tvContent.text = content
        binding.tvTgl.text = tgl
    }
}