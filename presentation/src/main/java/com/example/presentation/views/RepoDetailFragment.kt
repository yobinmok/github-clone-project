package com.example.presentation.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.repository.RepoRepository
import com.example.presentation.R
import com.example.presentation.adapter.TopicListAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentRepoDetailBinding
import com.example.presentation.viewModel.RepositoryViewModel

class RepoDetailFragment: BaseFragment<FragmentRepoDetailBinding, RepositoryViewModel>(R.layout.fragment_repo_detail) {

    override val viewModel: RepositoryViewModel by activityViewModels()
    override fun initViewCreated() {
        val args: RepoDetailFragmentArgs by navArgs()
        binding.item = args.repository
        binding.topicList.adapter = TopicListAdapter()
        binding.topicList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}