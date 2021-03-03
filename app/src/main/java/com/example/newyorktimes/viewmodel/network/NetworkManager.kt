package com.example.newyorktimes.viewmodel.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private val returnedData = "web_url, abstract"

    fun createService(): ArticlesRepository {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(ArticlesRepository.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(ArticlesRepository::class.java)
    }

    fun createData(category: String): MutableMap<String, String>{
        val data: MutableMap<String, String> = HashMap()
        data["api-key"] = ArticlesRepository.API_KEY
        data["fq"] = category
        data["fl"] = returnedData
        return data
    }
}