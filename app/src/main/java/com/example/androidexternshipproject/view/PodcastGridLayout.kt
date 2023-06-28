package com.example.androidexternshipproject.view

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.androidexternshipproject.R
import com.example.androidexternshipproject.apiInteractions.Result
import com.example.androidexternshipproject.model.PodcastData
import com.example.androidexternshipproject.stripHtml
import com.example.androidexternshipproject.ui.theme.Purple80
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ExperimentalFoundationApi
@Composable
fun PodcastGrid(navController: NavController, results: List<Result>) {
//    val context = LocalContext.current
//    val dataFileString = getJsonDataFromAsset(context, "PodcastList.json")
//    val gson = Gson()
//    val gridSampleType = object : TypeToken<List<PodcastData>>() {}.type
//    val PodcastData: List<PodcastData> = gson.fromJson(dataFileString, gridSampleType)
      val PodcastData = results

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Purple80)
                .padding(6.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Podcast Description",
                color = Purple80,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold

            )


        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(10.dp)
        ){
//            items(PodcastData){data->
            itemsIndexed(PodcastData){index, data ->
                PodcastGridItem(data,navController, index)
            }

        }

    }
}



@Composable
//fun PodcastGridItem(data:PodcastData,navController:NavController) {
fun PodcastGridItem(data:Result,navController:NavController, index:Int) {
    Card(modifier = Modifier
        .clickable {
//            val itemVal = Gson().toJson(data)
//            navController.navigate("grid_details/$itemVal")
            navController.navigate("grid_details/$index")
        }
        .padding(10.dp)
        .fillMaxSize(),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(modifier = Modifier) {

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
//                contentDescription = "Grid Image",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .align(Alignment.CenterHorizontally)
//                    .clip(RoundedCornerShape(5.dp)),
//                alignment = Alignment.Center
//            )
            AsyncImage(
                model = data.thumbnail,
                contentDescription = "Grid Image",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(5.dp)),
                alignment = Alignment.Center)

            Spacer(modifier = Modifier.padding(3.dp))
            Text(
//                text = data.name,
                text = stripHtml(data.titleOriginal),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
//                text = data.desc,
                text = stripHtml(data.descriptionOriginal),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(7.dp, 0.dp, 0.dp, 20.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )


        }

    }
}

//fun getJsonDataFromAsset(context: Context, data: String): String {
//    return context.assets.open(data).bufferedReader().use { it.readText() }
//
//}

