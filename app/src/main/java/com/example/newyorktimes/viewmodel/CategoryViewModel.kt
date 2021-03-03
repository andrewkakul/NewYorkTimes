package com.example.newyorktimes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.newyorktimes.R

class CategoryViewModel(application: Application) : AndroidViewModel(application){

    fun getCategoryList(): Array<out String> {
        return getApplication<Application>().resources.getStringArray(R.array.category_list)
    }
}