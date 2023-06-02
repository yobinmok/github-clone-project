package com.example.presentation.views

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.presentation.R
import com.example.presentation.adapter.RepoListAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentLikedBinding
import com.example.presentation.viewModel.RepositoryViewModel
import com.example.presentation.viewModel.UserViewModel

class LikedFragment : BaseFragment<FragmentLikedBinding, RepositoryViewModel>(R.layout.fragment_liked) {

    override val viewModel: RepositoryViewModel by activityViewModels()

    override fun initViewCreated() {
        val adapter = RepoListAdapter({repository ->
            Log.d("repoCheck", repository.toString())
            val action =
                LikedFragmentDirections.actionLikedFragmentToRepoDetailFragment2(repository)
            findNavController().navigate(action)
        }, { repo ->
            viewModel.setLiked(repo)
        })

        binding.apply {
            viewModel = this@LikedFragment.viewModel
            repoRecyclerView.adapter = adapter
            repoRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        }

        viewModel.requestLikedRepo()
    }
}