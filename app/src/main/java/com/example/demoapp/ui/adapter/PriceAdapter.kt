package com.example.demoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.databinding.ItemCategoryBinding
import com.example.demoapp.databinding.ItemListProductBinding
import com.example.demoapp.responses.MainListModel
import com.example.demoapp.utils.AdapterClickListener

class PriceAdapter(
    var list: ArrayList<String>,
    private val listener: AdapterClickListener
) :
    RecyclerView.Adapter<PriceAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: String) {
            val split = data.split("-")
            binding.txtName.text = split[0] + " - " + split[1]

            binding.root.setOnClickListener {
                val split = data.split("-")
                listener.getclickedPrice(split[0].toInt(), split[1].toInt())
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