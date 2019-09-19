package com.david.retrofitdemo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // GET https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UCZNZj3mkdCGJfCoKyl4bSYQ&maxResults=20&key={YOUR_API_KEY}
    //https://www.googleapis.com/youtube/v3/playlistItems?part=contentDetails&playlistId=PLWIMQHoO4EawaFZ34goa652mBtR4eR8hY&key=AIzaSyDEQ6Aih300ilCY9XuXsjyyi0YiTP72dhk

    @GET("playlists?part=snippet")
    fun tumListeleriGetir(@Query("channelId")channelID : String, @Query("key")apiKey:String,@Query("maxResults")limit:Int):Call<PlayListData>

}