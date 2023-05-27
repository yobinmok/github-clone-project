package com.example.presentation.views

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.presentation.viewModel.UserViewModel
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, UserViewModel>(R.layout.fragment_detail) {

    override val viewModel: UserViewModel by viewModels()

    override fun initViewCreated() {
        val args: DetailFragmentArgs by navArgs()
        viewModel.requestUser(args.login)
        binding.viewModel = viewModel
    }
}