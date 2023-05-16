package com.example.presentation.views

import android.util.Log
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentRepositoryBinding
import com.example.presentation.viewModel.UserViewModel

class RepositoryFragment : BaseFragment<FragmentRepositoryBinding, UserViewModel>(R.layout.fragment_repository) {

    override lateinit var viewModel: UserViewModel
    override fun initViewCreated() {
        Log.d("RepositoryFragment", "")
    }
}