package com.example.comislist.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comislist.R

@Composable
fun ProfileScreen (){

        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column (
                modifier = Modifier
                    .align(Alignment.Center)
            ){
                Box (
                    modifier = Modifier
                        .size(250.dp)
                        .clip(CircleShape)
                        .align(Alignment.CenterHorizontally)
                ){
                    Image(painter = painterResource(R.drawable.about_image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                    )
                }
                Text(
                    text = "M. Dafa Aldian",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "dafaaldian25@gmail.com",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }

    }


@Preview
@Composable
fun ProfilePreview() {
    ProfileScreen()
}