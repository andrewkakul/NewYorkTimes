package com.example.newyorktimes.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newyorktimes.R
import com.example.newyorktimes.databinding.FragmentCategoryBinding
import com.example.newyorktimes.model.ArticlesResponse
import com.example.newyorktimes.view.adapters.CategoryAdapter
import com.example.newyorktimes.view.adapters.CategoryListener
import com.example.newyorktimes.viewmodel.ArticlesViewModel
import com.example.newyorktimes.viewmodel.CategoryViewModel
import com.example.newyorktimes.viewmodel.network.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment : Fragment(), CategoryListener {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var articlesViewModel: ArticlesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    private val TAG = "CategoryFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articlesViewModel = ViewModelProvider(requireActivity()).get(ArticlesViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        recyclerView = binding.categoryRV
        categoryAdapter = CategoryAdapter(categoryViewModel.getCategoryList(), this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = categoryAdapter
    }

    override fun onCategoryClicked(position: Int) {
        articlesViewModel.getData(categoryAdapter.getCategory(position))
        findNavController().navigate(R.id.action_categoryFragment_to_articlesFragment)
    }
}