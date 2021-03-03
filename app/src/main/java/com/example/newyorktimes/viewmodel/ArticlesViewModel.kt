package com.example.newyorktimes.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.newyorktimes.R
import com.example.newyorktimes.model.Article
import com.example.newyorktimes.model.ArticlesResponse
import com.example.newyorktimes.viewmodel.network.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlesViewModel(application: Application) : AndroidViewModel(application){

    val articlesList = MutableLiveData<ArrayList<Article>>()
    var articles = ArrayList<Article>()

    private val TAG = "CategoryFragment"

    private fun saveData(element: ArticlesResponse){
        responseProcessing(element)
        articlesList.postValue(articles)
    }

    private fun responseProcessing(element: ArticlesResponse) {
       articles.clear()
        element.response.docs.forEach {
            val article = Article(it.abstract, it.web_url)
            articles.add(article)
        }
    }

    fun getData(title: String){
        viewModelScope.launch(Dispatchers.IO) {
            val service = NetworkManager.createService()
            val data = NetworkManager.createData(title)
            val call = service.getArticles(data)

            call.enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                        call: Call<ArticlesResponse>,
                        response: Response<ArticlesResponse>
                ) {
                    saveData(response.body()!!)
                }

                override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                    Log.e(TAG, t.toString())
                }
            })
        }
    }
}