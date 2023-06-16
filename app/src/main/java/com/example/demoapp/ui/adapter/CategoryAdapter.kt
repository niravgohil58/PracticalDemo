package com.example.demoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.databinding.ItemCategoryBinding
import com.example.demoapp.databinding.ItemListProductBinding
import com.example.demoapp.responses.MainListModel
import com.example.demoapp.utils.AdapterClickListener

class CategoryAdapter(
    var list: ArrayList<String>,
    private val listener: AdapterClickListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: String) {
            binding.txtName.text = data

            binding.root.setOnClickListener {
                listener.getclickedCategory(data)
            }
        }
    }

    fun updateAllData(item: ArrayList<String>) {
        list = item
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}