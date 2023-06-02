package com.example.presentation.views

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.presentation.R
import com.example.presentation.adapter.RepoListAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentRepositoryBinding
import com.example.presentation.viewModel.RepositoryViewModel

class RepositoryFragment :
    BaseFragment<FragmentRepositoryBinding, RepositoryViewModel>(R.layout.fragment_repository) {

    override val viewModel: RepositoryViewModel by activityViewModels()
    override fun initViewCreated() {
        val adapter = RepoListAdapter({ repository ->
            val action =
                RepositoryFragmentDirections.actionRepositoryFragmentToRepoDetailFragment(repository)
            Log.d("RepositoryFragment", repository.toString())
            findNavController().navigate(action)
        }, { repo ->
            viewModel.setLiked(repo)
        })
        binding.apply {
            viewModel = this@RepositoryFragment.viewModel
            repoRecyclerView.adapter = adapter
            repoRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        val args: RepositoryFragmentArgs by navArgs()
        viewModel.requestRepoList(args.repository)
//        if(viewModel.repoList.value.isEmpty()){
//
//        }
    }
}