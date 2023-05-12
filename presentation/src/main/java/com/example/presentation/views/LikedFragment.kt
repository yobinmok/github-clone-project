package com.example.presentation.views

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentLikedBinding
import com.example.presentation.viewModel.UserViewModel

class LikedFragment : BaseFragment<FragmentLikedBinding, UserViewModel>(R.layout.fragment_liked) {

    override lateinit var viewModel: UserViewModel

    override fun initViewCreated() {

    }
}