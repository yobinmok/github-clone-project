package com.example.presentation.views

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMyinfoBinding
import com.example.presentation.viewModel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyInfoFragment: BaseFragment<FragmentMyinfoBinding, UserViewModel>(R.layout.fragment_myinfo) {

    override val viewModel: UserViewModel by activityViewModels()
    private lateinit var sharedPreference: SharedPreferences
    override fun initViewCreated() {
        sharedPreference = requireActivity().getSharedPreferences("setMyInfo", Context.MODE_PRIVATE)
        val setInfo: Boolean = sharedPreference.getBoolean("setMyInfo", false)
        viewModel.setInfoStatus(setInfo)
        if(setInfo)
            viewModel.requestMyInfo()

        binding.viewModel = viewModel
        binding.myInfoLogin.setOnEditorActionListener { _, action, _ ->
            if(action == EditorInfo.IME_ACTION_DONE){
                val inputManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(binding.myInfoLogin.windowToken, 0)
                // API 호출
                viewModel.insertMyInfo(binding.myInfoLogin.text.toString())
                setPreference(true)
                viewModel.setInfoStatus(true)
                true
            }else
                false
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.insertResult.collect{
                Log.d("호출된거냐 만거냐!!!!!!!!!!!!!!", it.toString())
                if(it) viewModel.requestMyInfo()
            }
        }

        setupMenu()
    }

    private fun deleteInfo(){
        setPreference(false)
        viewModel.setInfoStatus(false)
        viewModel.deleteMyInfo()
    }

    private fun setPreference(setInfo: Boolean){
        var editor = sharedPreference.edit()
            .putBoolean("setMyInfo", setInfo)
            .commit()
    }

    private fun setupMenu(){
        // MenuHost API 사용해 MenuProvider 호출
        // https://medium.com/tech-takeaways/how-to-migrate-the-deprecated-oncreateoptionsmenu-b59635d9fe10
        // https://stackoverflow.com/questions/71917856/sethasoptionsmenuboolean-unit-is-deprecated-deprecated-in-java
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.myinfo_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.delete -> {
                        deleteInfo()
                        true
                    }
                    else -> {true}
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}