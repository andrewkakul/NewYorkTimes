package com.example.newyorktimes.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newyorktimes.R

class CategoryAdapter(private var categoryList: Array<out String>, private var categoryListener: CategoryListener ): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View, categoryListener: CategoryListener): RecyclerView.ViewHolder(view), View.OnClickListener{
        private var onCategoryTouched: CategoryListener
        private var categoryTitle: TextView? = null

        init {
            categoryTitle = itemView.findViewById(R.id.category_title)
            this.onCategoryTouched = categoryListener

            itemView.setOnClickListener(this)
        }

        fun bind(title: String){
            categoryTitle?.text = title
        }

        override fun onClick(v: View?) {
             onCategoryTouched.onCategoryClicked(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(itemView, categoryListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun getCategory(position: Int): String{
        return categoryList[position]
    }
}

interface CategoryListener {
    fun onCategoryClicked(position: Int)
}