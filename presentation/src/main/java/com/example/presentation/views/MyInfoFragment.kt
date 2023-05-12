package com.example.presentation.views

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMyinfoBinding
import com.example.presentation.viewModel.UserViewModel

class MyInfoFragment: BaseFragment<FragmentMyinfoBinding, UserViewModel>(R.layout.fragment_myinfo) {

    override lateinit var viewModel: UserViewModel
    override fun initViewCreated() {

    }
}