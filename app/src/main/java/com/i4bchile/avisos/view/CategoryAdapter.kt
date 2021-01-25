package com.i4bchile.avisos.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.i4bchile.avisos.databinding.ItemListCategoryBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryVH>() {

    val categoryList=mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val binding=ItemListCategoryBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryVH(binding)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val category=categoryList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun updateCategories(list: List<String>) {
        Log.d("TAG", "updateCategories: en adapter: ${list.size}")
        categoryList.clear()
        categoryList.addAll(list)
        notifyDataSetChanged()

    }
}

class CategoryVH (val binding:ItemListCategoryBinding): RecyclerView.ViewHolder(binding.root) {


    fun bind(category: String) {
        Log.d("TAG", "bind: Category:$category")
        binding.tvCategoria.text=category

    }

}
