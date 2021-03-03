package com.example.newyorktimes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.newyorktimes.model.Article
import com.example.newyorktimes.model.ArticlesResponse

class ArticlesViewModel(application: Application) : AndroidViewModel(application){

    val articlesList = MutableLiveData<ArrayList<Article>>()
    var articles = ArrayList<Article>()

    fun saveData(element: ArticlesResponse){
        responseProcessing(element)
        articlesList.postValue(articles)
    }

    fun responseProcessing(element: ArticlesResponse) {
       articles.clear()
        element.response.docs.forEach {
            val article = Article(it.abstract, it.web_url)
            articles.add(article)
        }
    }
}