package com.example.demoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.databinding.ItemListProductBinding
import com.example.demoapp.responses.MainListModel
import com.example.demoapp.utils.AdapterClickListener

class ListAdapter(
    var list: ArrayList<MainListModel>,
    private val listener: AdapterClickListener
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: MainListModel) {
            binding.apply {
                txtName.text = data.category
                txtDec.text = data.description
                txtPrice.text = "$" + data.price.toString()

                Glide.with(root.context)
                    .load(data.image)
                    .into(ivProduct)

                root.setOnClickListener {
                    listener.getclickedList(data)
                }
            }
        }
    }

    fun updateAllData(item: ArrayList<MainListModel>) {
        list = item
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}