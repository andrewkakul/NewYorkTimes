package com.example.newyorktimes.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newyorktimes.R
import com.example.newyorktimes.model.Article

class ArticlesAdapter(): RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private  var articleList = ArrayList<Article>()
    private lateinit var articleListener: ArticleListener

    constructor(articleListener: ArticleListener): this() {
        this.articleListener = articleListener

    }

    class ViewHolder(view: View, articleListener: ArticleListener): RecyclerView.ViewHolder(view), View.OnClickListener{
        private var onArticleTouched: ArticleListener
        private var articleTitle: TextView? = null

        init {
            articleTitle = itemView.findViewById(R.id.article_title)
            this.onArticleTouched = articleListener

            itemView.setOnClickListener(this)
        }

        fun bind(article: Article){
            articleTitle?.text = article.abstract
        }

        override fun onClick(v: View?) {
            onArticleTouched.onArticleClicked(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(itemView, articleListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int {
       return articleList.size
    }

    fun getArticle(position: Int): Article{
        return articleList[position]
    }

    fun setData(articles: ArrayList<Article>){
        articleList = articles
        notifyDataSetChanged()
    }
}

interface ArticleListener {
    fun onArticleClicked(position: Int)
}