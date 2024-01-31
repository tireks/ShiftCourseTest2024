package com.tirexmurina.viewxmlrandomusr.screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.tirexmurina.viewxmlrandomusr.databinding.FragmentHomeBinding
import com.tirexmurina.viewxmlrandomusr.domain.entity.User
import com.tirexmurina.viewxmlrandomusr.presentation.HomeAdapter
import com.tirexmurina.viewxmlrandomusr.presentation.HomeViewModel
import com.tirexmurina.viewxmlrandomusr.presentation.HomeViewState
import com.tirexmurina.viewxmlrandomusr.utils.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel : HomeViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecyclerView.adapter = HomeAdapter(::handleItemClick)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(homeViewState: HomeViewState) {
        when(homeViewState){
            HomeViewState.Initial -> Unit
            is HomeViewState.Content -> showContent(homeViewState.data)
            is HomeViewState.Error -> showError(homeViewState.errorMsg)
            is HomeViewState.Loading -> showProgress()
        }
    }

    private fun showError(errorMsg: String) {
        with(binding){
            progressBar.isVisible = false
            recyclerViewContent.isVisible = false
            errorContent.isVisible = true
            errorText.text = errorMsg
            errorButton.setOnClickListener {
                handleRestart()
            }
        }
    }

    private fun showContent(users: List<User>) {
        with(binding){
            progressBar.isVisible = false
            errorContent.isVisible = false
            recyclerViewContent.isVisible = true
            (homeRecyclerView.adapter as? HomeAdapter)?.users = users
            buttonRefresh.setOnClickListener{
                handleRefresh()
            }
        }
    }

    private fun handleRestart() {
        mainActivity.restart()
    }

    private fun handleRefresh() {
        viewModel.clearDb()
        handleRestart()
    }

    private fun showProgress() {
        with(binding) {
            errorContent.isVisible = false
            recyclerViewContent.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun handleItemClick(user: User) {
        try {
            user.id.let{mainActivity.openDetails(it) }
        }catch (e: Exception){
            showError(e.message.toString())
        }
    }

}