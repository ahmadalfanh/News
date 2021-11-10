package com.alfan.jakartapostapplication.data.network

import com.alfan.jakartapostapplication.data.respone.ArticlesResponse
import com.alfan.jakartapostapplication.data.respone.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://dev-api.thejakartapost.com/v1/"
    }
    @GET("articles/seasia?")
    suspend fun getArticles(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Data
}