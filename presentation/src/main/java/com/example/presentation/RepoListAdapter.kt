package com.example.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Repository
import com.example.domain.model.SearchUser
import com.example.presentation.databinding.RepoItemBinding
import com.example.presentation.databinding.UserItemBinding

class RepoListAdapter constructor (val clickListener: (String) -> Unit)
    : ListAdapter<Repository, RepoListAdapter.ViewHolder>(DiffUtil){

    class ViewHolder(private var itemBinding: RepoItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(repo: Repository){
            itemBinding.item = repo
            itemBinding.executePendingBindings() // 바인딩 데이터 즉각 변경
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener(item.name)
        }
    }

    companion object{
        val DiffUtil = object: DiffUtil.ItemCallback<Repository>(){
            override fun areItemsTheSame(old: Repository, new: Repository): Boolean {
                return old.id == new.id
            }

            override fun areContentsTheSame(old: Repository, new: Repository): Boolean {
                return old == new
            }
        }
    }
}