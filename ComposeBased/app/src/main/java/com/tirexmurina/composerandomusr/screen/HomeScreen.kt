package com.tirexmurina.composerandomusr.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
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
    onItemClick: (String) -> Unit,
    onRefreshClick: () -> Unit
){

    val viewState by remember { viewModel.state }
    when(val bufState = viewState){
        is HomeViewState.Content -> {
            HomeScreenContent(
                listUsers = bufState.data,
                onItemClick = { onItemClick(it) },
                onRefreshClick = {
                    viewModel.clearDb() //  хз, может есть способ лучше, пока напрямую дергается вьюмоделька
                    onRefreshClick()
                }
            )
        }

        is HomeViewState.Error -> {
                Text(text = "Success ${bufState.errorMsg}")
        }

        is HomeViewState.Loading -> {
            Text(text = "Loading")
        }

        else -> {

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    listUsers : List<User>,
    onItemClick: (String) -> Unit,
    onRefreshClick: () -> Unit
){
    Scaffold (
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    onRefreshClick()
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.Refresh, "Small floating action button.")
            }
        }
    ){
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(listUsers) { item ->
                SingleUserItem(user = item, it, onItemClick = {
                    onItemClick(it)
                })
            }
        }
    }
}


@Composable
fun SingleUserItem(
    user: User,
    paddingValues: PaddingValues, //по-хорошему надо было через это паддинги настроить, но они уже были готовы, поэтому проигнорил
    onItemClick: (String) -> Unit
){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(user.id) }
            .fillMaxWidth(), elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
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
                Column {
                    Text(
                        text = user.id.substringAfter("|||"),
                        fontSize = 18.sp, fontWeight = Medium, fontStyle = Italic
                    )
                    Text(
                        text = user.id.substringBefore("|||"),
                        fontSize = 24.sp, fontWeight = Bold
                    )
                }
            }
        }
    }

}