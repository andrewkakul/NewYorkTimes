package com.example.newyorktimes.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newyorktimes.databinding.FragmentArticlesBinding
import com.example.newyorktimes.view.adapters.ArticleListener
import com.example.newyorktimes.view.adapters.ArticlesAdapter
import com.example.newyorktimes.viewmodel.ArticlesViewModel

class ArticlesFragment : Fragment(), ArticleListener {

    private lateinit var binding: FragmentArticlesBinding
    private lateinit var articlesViewModel: ArticlesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.articlesRV
        articleAdapter = ArticlesAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = articleAdapter

        initViewModel()
    }

    private fun initViewModel(){
        articlesViewModel = ViewModelProvider(requireActivity()).get(ArticlesViewModel::class.java)
        articlesViewModel.articlesList.observe(viewLifecycleOwner,{
            articleAdapter.setData(it)
        })
    }

    override fun onArticleClicked(position: Int) {
        val action = ArticlesFragmentDirections.actionArticlesFragmentToDetailFragment(articleAdapter.getArticle(position).web_url)
        findNavController().navigate(action)
    }
}