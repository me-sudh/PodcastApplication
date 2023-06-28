package com.example.androidexternshipproject.view

import android.os.Build
import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androidexternshipproject.apiInteractions.Result
import com.example.androidexternshipproject.stripHtml
import com.example.androidexternshipproject.ui.theme.Purple80


@Composable
//fun PodcastDataDetails(data: PodcastData){
fun PodcastDataDetails(data: Result){
//    Column(modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center)
//    {
        Column(modifier = Modifier
            .fillMaxSize()
//            .fillMaxWidth()
//            .height(50.dp)
            .background(Purple80),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            Text(
////                text = "Podcast Description",
//                text = data.descriptionOriginal,
//                color = Color.White,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )
//            Spacer(modifier = Modifier.padding(1.dp))
//            Image(
//                painter = painterResource(
//                    id = when (data.id) {
//                        1L -> R.drawable.ic_launcher_background
//                        2L -> R.drawable.ic_launcher_background
//                        3L -> R.drawable.ic_launcher_background
//                        4L -> R.drawable.ic_launcher_background
//                        5L -> R.drawable.ic_launcher_background
//                        6L -> R.drawable.ic_launcher_background
//                        7L -> R.drawable.ic_launcher_background
//                        8L -> R.drawable.ic_launcher_background
//                        9L -> R.drawable.ic_launcher_background
//                        10L -> R.drawable.ic_launcher_background
//                        11L -> R.drawable.ic_launcher_background
//                        12L -> R.drawable.ic_launcher_background
//                        else -> R.drawable.ic_launcher_background
//                    }
//                ),
            AsyncImage(model = data.thumbnail,
                contentDescription = "Grid Image",
                modifier = Modifier
//                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
//                text = data.name,
                text = stripHtml(data.titleOriginal),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color= Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(1.dp))
            Text(
//                text = data.desc,
                text = stripHtml(data.descriptionOriginal),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(6.dp),
                color= Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }
    }
//}

