package com.example.androidexternshipproject

import android.os.Bundle
import android.text.Html
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.androidexternshipproject.apiInteractions.APIViewModel
import com.example.androidexternshipproject.app.PodcastApp
import com.example.androidexternshipproject.dbInteractions.RoomDb
import com.example.androidexternshipproject.dbInteractions.RoomDbDao
import com.example.androidexternshipproject.ui.theme.AndroidExternshipProjectTheme

class MainActivity : ComponentActivity() {
    val apiViewModel by viewModels<APIViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidExternshipProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    apiViewModel.search("")
                    lateinit var myDao:RoomDbDao
                    val db: RoomDb =
                        Room.inMemoryDatabaseBuilder(applicationContext, RoomDb::class.java).build()
                    myDao = db.roomDbDao()

                    PodcastApp(myDao, apiViewModel.searchResponse)            //for login/register

                }
            }
        }
    }
}


fun stripHtml(html: String?): String {
    return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
}