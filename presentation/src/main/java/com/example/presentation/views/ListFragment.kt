package com.example.presentation.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
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
        binding.viewModel = this@ListFragment.viewModel
    }
    override fun initViewCreated() {
        binding.userButton.setOnClickListener{
            if(binding.search.text.toString() == ""){
                Toast.makeText(requireContext(), "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                val action = ListFragmentDirections.actionListFragmentToUserFragment(binding.search.text.toString())
                findNavController().navigate(action)
            }
        }
        binding.repoButton.setOnClickListener{
            if(binding.search.text.toString() == ""){
                Toast.makeText(requireContext(), "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                val action = ListFragmentDirections.actionListFragmentToRepositoryFragment(binding.search.text.toString())
                findNavController().navigate(action)
            }
        }
    }
}
