package com.example.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.SearchUser
import com.example.presentation.databinding.UserItemBinding

class RecyclerViewAdapter constructor (val clickListener: (String) -> Unit)
    : ListAdapter<SearchUser, RecyclerViewAdapter.ViewHolder>(DiffUtil){

    class ViewHolder(private var itemBinding: UserItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(searchUser: SearchUser){
            itemBinding.item = searchUser
            itemBinding.executePendingBindings() // 바인딩 데이터 즉각 변경
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        val DiffUtil = object: ItemCallback<SearchUser>(){
            override fun areItemsTheSame(oldSearchUser: SearchUser, newSearchUser: SearchUser): Boolean {
                return oldSearchUser.id == newSearchUser.id
            }

            override fun areContentsTheSame(oldSearchUser: SearchUser, newSearchUser: SearchUser): Boolean {
                return oldSearchUser == newSearchUser
            }
        }
    }
}