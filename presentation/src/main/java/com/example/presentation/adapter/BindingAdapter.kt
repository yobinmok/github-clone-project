package com.example.presentation.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.NetworkResult
import com.example.domain.model.Repository
import com.example.domain.model.SearchUser
import com.example.presentation.R

@BindingAdapter("userListData")
fun bindUserList(recyclerView: RecyclerView, data: List<SearchUser>?){
    val adapter = recyclerView.adapter as UserListAdapter
    if (data != null) {
        adapter.submitList(data)
    }
}

@BindingAdapter("topicListData")
fun bindTopicList(recyclerView: RecyclerView, data: List<String>){
    val adapter = recyclerView.adapter as TopicListAdapter
    adapter.submitList(data)
}

@BindingAdapter("repoListData")
fun bindRepoList(recyclerView: RecyclerView, data: List<Repository>?){
    val adapter = recyclerView.adapter as RepoListAdapter
    if (data != null) {
        adapter.submitList(data)
        Log.d("BindingAdapter", data.toString())
    }
}

@BindingAdapter("imgSrc")
fun bindImageView(imgView: ImageView, data: String){
    Glide.with(imgView.context)
        .load(data)
        .into(imgView)
}

@BindingAdapter("resultStatus")
fun bindStatus(view: View, status: NetworkResult) {
    if(view.id == R.id.loading){
        when (status) {
            NetworkResult.SUCCESS -> view.visibility = View.GONE
            else -> view.visibility = View.VISIBLE
        }
    }else{
        when (status) {
            NetworkResult.SUCCESS -> view.visibility = View.VISIBLE
            else -> view.visibility = View.GONE
        }
    }

}

@BindingAdapter("emptyMsg")
fun bindEmptyMsg(textView: TextView, str: String){
    if(str != ""){
        textView.text = str
    }
}