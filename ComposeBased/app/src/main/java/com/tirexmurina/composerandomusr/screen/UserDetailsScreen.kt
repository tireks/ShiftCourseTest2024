package com.tirexmurina.composerandomusr.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.compose.rememberAsyncImagePainter
import com.tirexmurina.composerandomusr.R
import com.tirexmurina.composerandomusr.domain.entity.User
import com.tirexmurina.composerandomusr.presentation.UserViewModel
import com.tirexmurina.composerandomusr.presentation.UserViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.internal.toHexString

@Composable
fun UserDetailsScreen(
    viewModel: UserViewModel = hiltViewModel(),
    id : String?
) {

    Log.d("BL", "RECOMPOSED-USER_SCREEN")
    Log.d("BL","viewModel ${viewModel.hashCode().toHexString()}")
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
            UserDetailsScreenContent(user = bufState.data)
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
fun UserDetailsScreenContent(user: User){
    Column {
        Box(modifier = Modifier
            .height(230.dp)
            //.padding(16.dp)
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
        Text (
            //text = user.name.title,
            text = user.id.substringAfter("|||"),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 4.dp, top = 4.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic
        )
        Text (
            //text = user.name.first + " " + user.name.last,
            text = user.id.substringBefore("|||"),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 4.dp, top = 4.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text (
            text = user.gender,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 4.dp, top = 4.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic
        )
        Text (
            text = "Email:",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
        Text (
            text = user.email,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = "Cell:",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
        Text (
            text = user.cell,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = "Nationality:",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
        Text (
            text = user.nat,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = "Location:",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
        Text (
            text = user.location.country,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = user.location.state,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = user.location.city,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = user.location.street.name,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = user.location.street.number,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
        Row(){
            Column(modifier = Modifier
                .padding(start = 16.dp)
            ) {
                Text (
                    text = "Latitude:",
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 4.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
                Text (
                    text = user.location.coordinates.latitude,
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 4.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Column(modifier = Modifier
                .padding(start = 32.dp)
            ) {
                Text (
                    text = "Longitude:",
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 4.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
                Text (
                    text = user.location.coordinates.longitude,
                    modifier = Modifier
                        .padding(bottom = 4.dp, top = 4.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    //UserDetailsScreen()
}