package com.example.presentation.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.presentation.R
import com.example.presentation.RecyclerViewAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentListBinding
import com.example.presentation.viewModel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding, UserViewModel>(R.layout.fragment_list) {

    // by viewModels()에 의해 Hilt 적용 안해도 자동으로 viewModel 주입
    // viewModel 클래스에 @HiltViewModel 사용
    override val viewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecyclerViewAdapter { login ->
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(login)
            findNavController().navigate(action)
        }

        binding.apply {
            viewModel = this@ListFragment.viewModel
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        }
    }
    override fun initViewCreated() {
        binding.userButton.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUserFragment(binding.search.text.toString())
            findNavController().navigate(action)
        }
        binding.repoButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_repositoryFragment)
        }
    }
}
