package com.tirexmurina.viewxmlrandomusr.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tirexmurina.viewxmlrandomusr.R
import com.tirexmurina.viewxmlrandomusr.databinding.FragmentHomeBinding
import com.tirexmurina.viewxmlrandomusr.databinding.FragmentUserDetailsBinding
import com.tirexmurina.viewxmlrandomusr.domain.entity.User
import com.tirexmurina.viewxmlrandomusr.presentation.HomeViewModel
import com.tirexmurina.viewxmlrandomusr.presentation.HomeViewState
import com.tirexmurina.viewxmlrandomusr.presentation.UserDetailsViewModel
import com.tirexmurina.viewxmlrandomusr.presentation.UserViewState
import com.tirexmurina.viewxmlrandomusr.utils.mainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>() {

    private val viewModel : UserDetailsViewModel by viewModels()
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserDetailsBinding {
        return FragmentUserDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
        loadData()
    }

    private fun loadData() {
       viewModel.getUserById(args.userId)
    }

    private fun handleState(userViewState: UserViewState) {
        when(userViewState){
            UserViewState.Initial -> Unit
            is UserViewState.Content -> showContent(userViewState.data)
            is UserViewState.Error -> showError(userViewState.errorMsg)
            is UserViewState.Loading -> showProgress()
        }
    }

    private fun showProgress() {

    }

    private fun showError(errorMsg: String) {

    }

    private fun showContent(user: User) {
        with(binding){

            Glide.with(userPhoto.context)
                .load(user.picture.large)
                .placeholder(R.drawable.recycler_view_placeholder)
                .error(R.drawable.recycler_view_placeholder)
                .into(userPhoto)
            userTitle.text = user.name.title
            userName.text = "${user.name.first} ${user.name.last}"
            userGender.text = user.gender
            userEmail.text = user.email
            userCell.text = user.cell
            userNat.text = user.nat
            userCountry.text = user.location.country
            userState.text = user.location.state
            userCity.text = user.location.city
            userStreetName.text = user.location.street.name
            userStreetNum.text = user.location.street.number
            userLat.text = user.location.coordinates.latitude
            userLong.text= user.location.coordinates.longitude

            userEmail.setOnClickListener{
                mainActivity.emailPerson(user.email)
            }
            coordsContainer.setOnClickListener{
                mainActivity.showMap(user.location.coordinates)
            }
            userCell.setOnClickListener {
                mainActivity.dialPhoneNumber(user.cell)
            }
        }
    }

}