package com.example.androidexternshipproject.apiInteractions

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//testing without retrofit
//class APIViewModel: ViewModel() {
//    private val api = APIService()
//    var results:List<Result> by mutableStateOf(listOf())
//
//    fun search(q:String, //search term
//               sort_by_date:Int = 0, //0 for relevance, 1 for date
//               type:String = "episode", //episode, podcast, curated
//               offset:Int = 0, //page number
//        ) {
//        viewModelScope.launch {
//            results = api.search(q, sort_by_date, type, offset)?.results!!
//        }
//    }
//}

class APIViewModel : ViewModel() {

    var searchResponse:List<Result> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun search(q:String) {
        viewModelScope.launch {
            Log.d("a:","TEST1")
            val apiService = APIService.getInstance()
            try {
                Log.d("a:","TEST2")
                searchResponse= apiService.getSearch(q).results
                Log.d("a:","TEST3")
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e("error:", errorMessage)
            }
        }
    }
}