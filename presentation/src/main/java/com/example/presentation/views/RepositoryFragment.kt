package com.example.presentation.views

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.presentation.R
import com.example.presentation.RepoListAdapter
import com.example.presentation.UserListAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentRepositoryBinding
import com.example.presentation.viewModel.RepositoryViewModel
import com.example.presentation.viewModel.UserViewModel

class RepositoryFragment : BaseFragment<FragmentRepositoryBinding, RepositoryViewModel>(R.layout.fragment_repository) {

    override val viewModel: RepositoryViewModel by activityViewModels()
    override fun initViewCreated() {
        val adapter = RepoListAdapter { reponame ->
            val action = RepositoryFragmentDirections.actionRepositoryFragmentToRepoDetailFragment(reponame)
            Log.d("RepositoryFragment", reponame)
            findNavController().navigate(action)
        }

        binding.apply {
            viewModel = this@RepositoryFragment.viewModel
            repoRecyclerView.adapter = adapter
            repoRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        val args: RepositoryFragmentArgs by navArgs()
        viewModel.requestRepoList(args.repository)
    }
}