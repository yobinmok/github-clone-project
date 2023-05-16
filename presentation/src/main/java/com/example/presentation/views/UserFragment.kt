package com.example.presentation.views

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.presentation.R
import com.example.presentation.RecyclerViewAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentUserBinding
import com.example.presentation.viewModel.UserViewModel

class UserFragment: BaseFragment<FragmentUserBinding, UserViewModel>(R.layout.fragment_user) {

    override val viewModel: UserViewModel by activityViewModels()

    override fun initViewCreated() {
        val adapter = RecyclerViewAdapter { login ->
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(login)
            findNavController().navigate(action)
        }

        binding.apply {
            viewModel = this@UserFragment.viewModel
            userRecyclerView.adapter = adapter
            userRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        val args: UserFragmentArgs by navArgs()
        viewModel.requestUserList(args.login)
    }
}