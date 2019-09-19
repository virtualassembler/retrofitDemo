package com.david.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val API_KEY = "AIzaSyDEQ6Aih300ilCY9XuXsjyyi0YiTP72dhk"
    val CHANNEL_ID = "UCZNZj3mkdCGJfCoKyl4bSYQ"
    var gelenVeri : PlayListData? = null
    var oynatmaListeleri : List<PlayListData.Items>? = null

    var myAdapter : PlayListAdapter? = null

    lateinit var playListView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playListView = findViewById(R.id.recyclerviewPlaylist)

        // refrofit ile interface arasında bağlantı kuruldu.
        var apiInterface = ApiClient.client?.create(ApiInterface::class.java) // retrofit varsa devam et yoksa oluştur ve işlemi başlat
        var apiCall = apiInterface?.tumListeleriGetir(CHANNEL_ID,API_KEY,20)

        apiCall?.enqueue(object : Callback<PlayListData>{

            override fun onFailure(call: Call<PlayListData>?, t: Throwable?) { // basarısızsa

                Log.e("HATALI",""+t?.printStackTrace())
            }

            override fun onResponse(call: Call<PlayListData>?, response: Response<PlayListData>?) { // basarılıysa

                Log.e("BASARILI",""+call?.request()?.url()?.toString())

                gelenVeri = response?.body()
                oynatmaListeleri = gelenVeri?.items


                myAdapter = PlayListAdapter(oynatmaListeleri)
                playListView.adapter = myAdapter

                var myLayoutManager = LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL,false)
                playListView.layoutManager = myLayoutManager

                supportActionBar?.setSubtitle("Toplam Liste : "+oynatmaListeleri?.size)

                Log.e("basarili","toplam liste sayisi"+oynatmaListeleri?.size)
                Log.e("basarili","toplam liste sayisi"+oynatmaListeleri?.size)
            }
        })
    }
}
