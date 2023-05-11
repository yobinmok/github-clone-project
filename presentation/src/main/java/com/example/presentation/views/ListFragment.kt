package com.example.presentation.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.viewModel.UserViewModel
import com.example.presentation.R
import com.example.presentation.RecyclerViewAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding, UserViewModel>(R.layout.fragment_list) {

    // by viewModels()에 의해 Hilt 적용 안해도 자동으로 viewModel 주입
    // viewModel 클래스에 @HiltViewModel 사용
    override val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecyclerViewAdapter { login ->
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(login)
            findNavController().navigate(action)
        }
        viewModel.requestAllData()
        binding.apply {
            viewModel = this@ListFragment.viewModel
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
    override fun initViewCreated() {

    }
}
