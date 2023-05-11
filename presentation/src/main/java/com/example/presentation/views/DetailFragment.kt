package com.example.presentation.views

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.presentation.ItemViewModel
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, ItemViewModel>(R.layout.fragment_detail) {

    override val viewModel: ItemViewModel by viewModels()

    override fun initViewCreated() {
        val args: DetailFragmentArgs by navArgs()
        viewModel.requestData(args.login)
        binding.viewModel = viewModel
    }
}