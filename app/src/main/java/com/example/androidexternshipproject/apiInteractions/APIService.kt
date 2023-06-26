package com.example.androidexternshipproject.apiInteractions

import com.listennotes.podcast_api.exception.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//testing without retrofit
//public class APIService {
//    val apiKey: String? = ""    //TODO: Put actual API key
//    val objClient = Client(apiKey)
//    val gson = Gson()
//
//    suspend fun search(q:String, //search term
//                sort_by_date:Int = 0, //0 for relevance, 1 for date
//                type:String = "episode", //episode, podcast, curated
//                offset:Int = 0, //page number
//                //more options available according to the API, could be implemented in the future
//    ): ResponseDataClass? {
//        val parameters = HashMap<String, String> ()
//        parameters["q"] = q
//        parameters["sort_by_date"] = sort_by_date.toString()
//        parameters["type"] = type
//        //TODO check for proper type value here
//        parameters["offset"] = offset.toString()
//        var response:ApiResponse? = null
//        try{
//            response = objClient.search(parameters)
//        }catch (e:ListenApiException){
//            println(e)
//        }
//        //handle response in some way
//        return gson.fromJson(response?.toJSON().toString(), ResponseDataClass::class.java)
//    }
//
//}

interface APIService{
    @GET("search")
    suspend fun getSearch(@Query("q") query:String) :ResponseDataClass
    //further parameters would be modified here, along with more functions

    companion object {
        var apiService: APIService? = null
        fun getInstance() : APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://listen-api-test.listennotes.com/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}