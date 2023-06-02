package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Repository
import com.example.presentation.R
import com.example.presentation.databinding.RepoItemBinding

class RepoListAdapter constructor(val viewClickListener: (Repository) -> Unit, val likedClickListener: (Repository) -> Unit) :
    ListAdapter<Repository, RepoListAdapter.ViewHolder>(DiffUtil) {

    class ViewHolder(private var itemBinding: RepoItemBinding, val clickListener: (Repository) -> Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(repo: Repository) {
            itemBinding.item = repo
            // 둘다 true여야 함
            itemBinding.repoTopics.apply {
                adapter = TopicListAdapter()
                layoutManager = LinearLayoutManager(
                    itemBinding.repoTopics.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            }
            itemBinding.liked.setOnClickListener {
                clickListener(repo)
                if(repo.liked) itemBinding.liked.setImageResource(R.drawable.ic_star)
                else itemBinding.liked.setImageResource(R.drawable.ic_filled_star)
            }
            itemBinding.executePendingBindings() // 바인딩 데이터 즉각 변경
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, likedClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            viewClickListener(item)
        }
    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(old: Repository, new: Repository): Boolean {
                return old.liked == new.liked
            }

            override fun areContentsTheSame(old: Repository, new: Repository): Boolean {
                return old == new
            }
        }
    }
}