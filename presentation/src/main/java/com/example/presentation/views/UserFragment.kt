package com.example.presentation.views

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.presentation.R
import com.example.presentation.UserListAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentUserBinding
import com.example.presentation.viewModel.UserViewModel

class UserFragment: BaseFragment<FragmentUserBinding, UserViewModel>(R.layout.fragment_user) {

    override val viewModel: UserViewModel by activityViewModels()

    override fun initViewCreated() {
        val adapter = UserListAdapter { login ->
            val action = UserFragmentDirections.actionUserFragmentToDetailFragment(login)
            Log.d("UserFragment", login)
            findNavController().navigate(action)
        }

        binding.apply {
            viewModel = this@UserFragment.viewModel
            userRecyclerView.adapter = adapter
            userRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        val args: UserFragmentArgs by navArgs()
        viewModel.requestUserList(args.login)
        viewModel.requestUser(args.login)
        binding.user.setOnClickListener {
            val action = UserFragmentDirections.actionUserFragmentToDetailFragment(args.login)
            findNavController().navigate(action)
        }
    }
}