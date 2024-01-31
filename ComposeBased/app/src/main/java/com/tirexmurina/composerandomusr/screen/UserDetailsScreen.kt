package com.tirexmurina.composerandomusr.screen

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.tirexmurina.composerandomusr.R
import com.tirexmurina.composerandomusr.data.Coordinates
import com.tirexmurina.composerandomusr.data.Location
import com.tirexmurina.composerandomusr.data.Name
import com.tirexmurina.composerandomusr.data.Picture
import com.tirexmurina.composerandomusr.data.Street
import com.tirexmurina.composerandomusr.data.Timezone
import com.tirexmurina.composerandomusr.domain.entity.User
import com.tirexmurina.composerandomusr.presentation.UserViewModel
import com.tirexmurina.composerandomusr.presentation.UserViewState
import okhttp3.internal.toHexString

@Composable
fun UserDetailsScreen(
    viewModel: UserViewModel = hiltViewModel(),
    id : String?
) {

    DisposableEffect(key1 = Unit){
        if(!id.isNullOrBlank()){
            viewModel.getUserById(id)
        }
        onDispose {}
    }

    val viewState by remember { viewModel.state }
    when(val bufState = viewState){
        is UserViewState.Initial ->{

        }

        is UserViewState.Content -> {
            val context = LocalContext.current
            UserDetailsScreenContent(
                user = bufState.data,
                onCoordinatesClick = { showMap(context, bufState.data.location.coordinates) },
                onDialerClick = { dialPhoneNumber(context, bufState.data.cell) },
                onEmailClick = { emailPerson(context, bufState.data.email) }
            )
        }
        is UserViewState.Error -> {
            Text(text = "Error ${bufState.errorMsg}")
        }
        is UserViewState.Loading -> {
            Text(text = "Loading")
            //todo тут чета сделать
        }
    }
}

@Composable
fun UserDetailsScreenContent(
    user: User,
    onCoordinatesClick: () -> Unit,
    onDialerClick:() -> Unit,
    onEmailClick: () -> Unit
){
    Column {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier
                .height(230.dp)
                .padding(top = 16.dp, bottom = 4.dp)
            ){
                Image(
                    painter = rememberAsyncImagePainter(
                        user.picture.large
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize()
                )
            }
            LabelTextHeader(text = user.name.title)
            ContentTextHeader(text = "${user.name.first} ${user.name.last}")
            LabelTextHeader(text = user.gender)
        }
        Column {
            LabelTextBody(text = "Email:")
            Row(modifier = Modifier.clickable { onEmailClick() }){
                ContentTextBody(text = user.email)
                Icon(painter = painterResource(id = R.drawable.baseline_touch_app_24),
                    contentDescription ="Small floating action button.")
            }
            LabelTextBody(text = "Cell:")
            Row(modifier = Modifier.clickable { onDialerClick() }){
                ContentTextBody(text = user.cell)
                Icon(painter = painterResource(id = R.drawable.baseline_touch_app_24),
                    contentDescription ="Small floating action button.")
            }
            LabelTextBody(text = "Nationality:")
            ContentTextBody(text = user.nat)
            LabelTextBody(text = "Location:")
            ContentTextBody(text = user.location.country)
            ContentTextBody(text = user.location.state,)
            ContentTextBody(text = user.location.city)
            Row {
                ContentTextBody(text = user.location.street.name)
                ContentTextBody(text = user.location.street.number)
            }
            Row(
                modifier = Modifier.clickable { onCoordinatesClick() }
            ){
                Column(modifier = Modifier
                ) {
                    LabelTextBody(text = "Latitude:")
                    ContentTextBody(text = user.location.coordinates.latitude)
                }
                Column(modifier = Modifier
                    .padding(start = 32.dp)
                ) {
                    LabelTextBody(text = "Longitude:")
                    ContentTextBody(text = user.location.coordinates.longitude)
                }
                Icon(
                    painter = painterResource(id = R.drawable.baseline_touch_app_24),
                    contentDescription ="Small floating action button.",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }

}

@Composable
fun LabelTextHeader(text : String){
    Text (
        text = text,
        modifier = Modifier
            .padding(bottom = 4.dp, top = 4.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun ContentTextHeader(text : String){
    Text (
        text = text,
        modifier = Modifier
            .padding(bottom = 4.dp, top = 4.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun LabelTextBody(text : String){
    Text (
        text = text,
        modifier = Modifier
            .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun ContentTextBody(text : String){
    Text (
        text = text,
        modifier = Modifier
            .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    )
}

// далее идут функции отправляющие в сторонние приложения, я не знал, где лучше это сделать.
// Вроде, это делается на уровне представления, значит либо в View, либо в ViewModel,
// но мне не хотелось передавть контекст, даже контекст приложения, в ViewModel,
// насколько я понимаю - это не очень хорошая практика,


fun showMap(context: Context, geoCoordinates: Coordinates) {
    val geoLocation =
        "geo:${geoCoordinates.latitude},${geoCoordinates.longitude}"
    val geoIntentUri = Uri.parse(geoLocation)
    val mapIntent = Intent(Intent.ACTION_VIEW, geoIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    mapIntent.resolveActivity(context.packageManager)?.let {
        startActivity(context, mapIntent, null)
    }
}

fun dialPhoneNumber(context: Context, phoneNumber: String){
    val phoneNumberUri = Uri.parse("tel:$phoneNumber")
    val dialerIntent = Intent(Intent.ACTION_DIAL, phoneNumberUri)
    Log.d("BK", "TRYING to DIAL")
    dialerIntent.resolveActivity(context.packageManager)?.let {
        startActivity(context, dialerIntent, null)
    }
}

fun emailPerson(context: Context, email: String) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply { 
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, email)
    }
    Log.d("BK", "TRYING to DIAL")
    emailIntent.resolveActivity(context.packageManager)?.let {
        startActivity(context, emailIntent, null)
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    UserDetailsScreenContent(
        user = User(
            name = Name(
                first = "Louanne",
                last = "Roger",
                title = "Ms"
            ),
            cell = "06-59-69-07-71",
            email = "louanne.roger@example.com",
            gender = "female",
            id = "2e4cf75fb419897c|||5",
            picture = Picture(
                large = "https://randomuser.me/api/portraits/women/65.jpg",
                medium = "https://randomuser.me/api/portraits/med/women/65.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/women/65.jpg"
            ),
            nat = "BR",
            location = Location(
                street = Street(
                    number = "3423",
                    name = "Rua São Sebastiao"
                ),
                city = "Macapá",
                state = "Pernambuco",
                country = "Brazil",
                postcode = "96927",
                coordinates = Coordinates(
                    latitude = "14.4052",
                    longitude = "160.4737"
                ),
                timezone = Timezone(
                    offset = "+5:30",
                    description = "Bombay, Calcutta, Madras, New Delhi"
                )
            )
        ),
        onCoordinatesClick = {},
        onDialerClick = {},
        onEmailClick = {}
    )
}