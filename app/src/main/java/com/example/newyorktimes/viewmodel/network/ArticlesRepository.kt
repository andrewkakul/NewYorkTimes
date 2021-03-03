package com.example.newyorktimes.viewmodel.network
import com.example.newyorktimes.model.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ArticlesRepository {

    companion object{
        const val API_KEY = "2rxKljg4eTfQx2zC4cSJu42TeA1PxxVM"
        const val BASE_URL = "https://api.nytimes.com/svc/search/v2/articlesearch.json/"
    }

   @GET(".")
   fun getArticles(
       @QueryMap options: Map<String, String>
   ): Call<ArticlesResponse>
}