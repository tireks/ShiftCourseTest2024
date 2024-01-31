package com.tirexmurina.viewxmlrandomusr

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.tirexmurina.viewxmlrandomusr.data.Coordinates
import com.tirexmurina.viewxmlrandomusr.databinding.ActivityMainBinding
import com.tirexmurina.viewxmlrandomusr.screen.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController get() = findNavController(R.id.mainDataContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openDetails(userId: String){
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToUserDetailsFragment(userId))
    }

    fun restart(){
        navController.navigate(HomeFragmentDirections.actionHomeFragmentSelf())
    }

    fun showMap(geoCoordinates: Coordinates){
        val geoLocation =
            "geo:${geoCoordinates.latitude},${geoCoordinates.longitude}"
        val geoIntentUri = Uri.parse(geoLocation)
        val mapIntent = Intent(Intent.ACTION_VIEW, geoIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(this.packageManager)?.let {
            ContextCompat.startActivity(this, mapIntent, null)
        }
    }

    fun dialPhoneNumber(phoneNumber: String){
        val phoneNumberUri = Uri.parse("tel:$phoneNumber")
        val dialerIntent = Intent(Intent.ACTION_DIAL, phoneNumberUri)
        dialerIntent.resolveActivity(this.packageManager)?.let {
            ContextCompat.startActivity(this, dialerIntent, null)
        }
    }

    fun emailPerson(email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, email)
        }
        emailIntent.resolveActivity(this.packageManager)?.let {
            ContextCompat.startActivity(this, emailIntent, null)
        }
    }
}