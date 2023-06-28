package com.example.androidexternshipproject

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.androidexternshipproject.apiInteractions.APIViewModel
import com.example.androidexternshipproject.apiInteractions.Result
import com.example.androidexternshipproject.app.PodcastApp
import com.example.androidexternshipproject.dbInteractions.CredentialsEntity
import com.example.androidexternshipproject.dbInteractions.RoomDb
import com.example.androidexternshipproject.dbInteractions.RoomDbDao
import com.example.androidexternshipproject.model.PodcastData
import com.example.androidexternshipproject.ui.theme.AndroidExternshipProjectTheme
import com.example.androidexternshipproject.view.PodcastDataDetails
import com.example.androidexternshipproject.view.PodcastGrid
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val apiViewModel by viewModels<APIViewModel>()
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidExternshipProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    PodcastApp()            //for login/register
                    //Greeting("Android")           //default function
                    //DBTest(applicationContext)     //testing db insert
//                    Column(modifier = Modifier.fillMaxSize()) {
//                        Button(onClick = { apiViewModel.search("mars") }) {
//                            Text("CLICK ME")
//                        }
//                        ApiTest(apiViewModel.searchResponse)        //testing api connection
//                    }
                    NavigatePage()         //for inside the app functionality

                }
            }
        }
    }
}
@Composable
fun ApiTest(result: List<Result>){
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn{
            if(result.isNotEmpty()){
                items(result){
                    Text(it.id)
                    Text(it.rss)
                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }else{
                item(){
                    Text("EMPTY")
                }

            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DBTest(context: Context){
    lateinit var myDao: RoomDbDao
    val db: RoomDb =
        Room.inMemoryDatabaseBuilder(context, RoomDb::class.java).build()
    myDao = db.roomDbDao()
    Display(myDao)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Display(dao: RoomDbDao){
    val scope = CoroutineScope(Dispatchers.Main)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        var user by remember{ mutableStateOf("") }
        var pass by remember{ mutableStateOf("") }
        TextField(value = user, onValueChange = {user = it}, label = {Text("Username")})
        TextField(value = pass, onValueChange = {pass = it}, label = {Text("Password")})
        Button(onClick = {
            val item = CredentialsEntity(email = user, password = pass)
            scope.launch(Dispatchers.IO) {
                dao.insert(item)
            }
            user = ""
            pass = ""
        }) {
            Text(text = "Insert to DB")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidExternshipProjectTheme {
        Greeting("Android")
    }
}


@ExperimentalFoundationApi
@Composable
fun NavigatePage(){
    val navHostController = rememberNavController()
    NavHost(navController =navHostController,
        startDestination = "podcast_data"){
        composable("podcast_data"){
            PodcastGrid(navController = navHostController)
        }
        composable("grid_details/{item}",
            arguments = listOf(
                navArgument("item"){
                    type = NavType.StringType
                }
            )
        ){
                navBackStackEntry ->
            navBackStackEntry.arguments?.getString("item")?.let { json->
                val item = Gson().fromJson(json, PodcastData::class.java)
                PodcastDataDetails(data = item)
            }
        }
    }
}