package com.example.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Item
import com.example.presentation.databinding.ListItemBinding

class RecyclerViewAdapter constructor (val clickListener: (String) -> Unit)
    : ListAdapter<Item, RecyclerViewAdapter.ViewHolder>(DiffUtil){

    class ViewHolder(private var itemBinding: ListItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(itemEntity: Item){
            itemBinding.item = itemEntity
            itemBinding.executePendingBindings() // 바인딩 데이터 즉각 변경
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener(item.login)
        }
    }

    companion object{
        val DiffUtil = object: ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}