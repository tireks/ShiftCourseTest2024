package com.tirexmurina.composerandomusr.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.tirexmurina.composerandomusr.domain.entity.User
import com.tirexmurina.composerandomusr.presentation.HomeViewModel
import com.tirexmurina.composerandomusr.presentation.HomeViewState
import okhttp3.internal.toHexString

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
){
    /*val listOfUsers by remember { viewModel.listOfUsers }
    Log.d("BL", "RECOMPOSED-HOME_SCREEN")
    Log.d("BL","viewModel ${viewModel.hashCode().toHexString()}")

    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(listOfUsers) { item ->
            SingleUserItem(user = item, onClick = {
                onItemClick(it)
            })
        }
    }*/


    Log.d("BL", "RECOMPOSED-HOME_SCREEN")
    Log.d("BL","viewModel ${viewModel.hashCode().toHexString()}")

    /*DisposableEffect(key1 = Unit){
        Log.d("BL", "EXCEPTION")
        viewModel.getUsers()
        onDispose {}
    }*/

    //viewModel.getUsers()

    val viewState by remember { viewModel.state }
    //val viewState by viewModel.state
    when(val bufState = viewState){
        is HomeViewState.Content -> {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(bufState.data) { item ->
                     SingleUserItem(user = item, onClick = {
                         onItemClick(it)
                     })
                }
            }
        }

        is HomeViewState.Error -> {
                Text(text = "Success ${bufState.errorMsg}")
            // todo хз, тестил отключая инет, еррор не переходит -upd. при кривом запросе выдает ошибку
        }

        is HomeViewState.Loading -> {
            Text(text = "Loading")
            //todo тут ченить сделать
        }

        else -> {

        }
    }
}

@Composable
fun SingleUserItem(
    user: User,
    onClick: (String) -> Unit
){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick(user.id) } // todo пока просто заглушка, передает вверх сид
            .fillMaxWidth(), elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        /*Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(80.dp), painter = rememberAsyncImagePainter(
                    category.strCategoryThumb
                ), contentDescription = null
            )
            Text(text = category.strCategory, fontSize = 24.sp)
        }*/
        Column() {
            Box(modifier = Modifier.height(230.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(
                        user.picture.large
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .padding(16.dp)
            ){
                /*Column {
                    Text(text = user.name.title, fontSize = 18.sp, fontWeight = Medium, fontStyle = Italic)
                    Text(text = user.name.first, fontSize = 24.sp, fontWeight = Bold)
                    Text(text = user.name.last, fontSize = 24.sp, fontWeight = Bold)
                }*/
                Column {
                    Text(text = user.id.substringAfter("|||"), fontSize = 18.sp, fontWeight = Medium, fontStyle = Italic)
                    Text(text = user.id.substringBefore("|||"), fontSize = 24.sp, fontWeight = Bold)
                }
            }
        }
    }

}