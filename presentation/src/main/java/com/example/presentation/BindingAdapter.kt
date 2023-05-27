package com.example.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Repository
import com.example.domain.model.SearchUser


@BindingAdapter("userListData")
fun bindUserList(recyclerView: RecyclerView, data: List<SearchUser>?){
    val adapter = recyclerView.adapter as UserListAdapter
    if (data != null) {
        adapter.submitList(data)
    }
}

@BindingAdapter("repoListData")
fun bindRepoList(recyclerView: RecyclerView, data: List<Repository>?){
    val adapter = recyclerView.adapter as RepoListAdapter
    if (data != null) {
        adapter.submitList(data)
    }
}

@BindingAdapter("imgSrc")
fun bindImageView(imgView: ImageView, data: String){
    Glide.with(imgView.context)
        .load(data)
        .into(imgView)
}
