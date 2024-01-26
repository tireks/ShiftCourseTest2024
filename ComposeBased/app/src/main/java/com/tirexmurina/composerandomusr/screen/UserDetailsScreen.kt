package com.tirexmurina.composerandomusr.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tirexmurina.composerandomusr.R

@Composable
fun UserDetailsScreen() {
    Column {
        Box(modifier = Modifier
            .height(230.dp)
            //.padding(16.dp)
            .padding(top = 16.dp, bottom = 4.dp)
        ){
            Image(
                painterResource(id = R.drawable.person0placeholder),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text (
            text = "Ms",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 4.dp, top = 4.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic
        )
        Text (
            text = "Rebecca Composinski",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 4.dp, top = 4.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text (
            text = "Female",
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
            text = "testmail@mail.com",
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
            text = "0176-2908287",
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
            text = "GB",
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
            text = "Finland",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = "Northern Savonia",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = "Kumlinge",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = "Suvantokatu" + " 1440",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text (
            text = "Coordinates:",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
        /*Text (
            text = "-25.1328" + "    -51.2658",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 4.dp, top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )*/
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
                    text = "-25.1328",
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
                    text = "-51.2658",
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
    UserDetailsScreen()
}