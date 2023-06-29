package com.example.androidexternshipproject.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
fun PodcastDataDetails(data: Result){
        Column(modifier = Modifier
            .fillMaxSize()

            .background(Purple80),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(model = data.thumbnail,
                contentDescription = "Grid Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp)),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
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

