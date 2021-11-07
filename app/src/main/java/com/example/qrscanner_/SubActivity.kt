package com.example.qrscanner_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sub.*

import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//!!!!!!!!!!!!!!!API적용할것!!!!!!!!!!!!!!!!!!!!!
//밑의 코드 Test Case를 직접 넣어서 사용함.

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val menuList = arrayListOf(
            Menus(R.drawable.rainy,"아메리카노",4000),
            Menus(R.drawable.rainy,"카페라떼",5000)
        )

        rv_menuList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_menuList.setHasFixedSize(true)
        rv_menuList.adapter = MenuAdapter(menuList)

        // listView 생성 -> 나중에 아래로 들어갈것.!
        /*
        val menu_list = arrayListOf(
            Menus(R.drawable.rainy,"아메리카노",4000), //test데이터 -> 서버에서 받아서 사용할것.
            Menus(R.drawable.sunny,"카페라떼",40001),
            Menus(R.drawable.sunny,"바닐라라떼",40002),
            Menus(R.drawable.sunny,"카페모카",40003),
            Menus(R.drawable.sunny,"아이스아메리카노",40004),
        )
        */

        //rv_menuList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        //rv_menuList.setHasFixedSize(true)
        //rv_menuList.adapter = MenuAdapter(menu_list)
       //


        /*
        if(intent.hasExtra("testurl")){
            //test_Lbl.text = intent.getStringExtra("testurl")

            //api test start
            val url = "http://api.openweathermap.org/"
            val url2 = intent.getStringExtra("testurl")
            val retrofit = Retrofit.Builder()
                .baseUrl(url2)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(WeatherService::class.java)
            val call = service.getCurrentWeatherData(q = "Seoul",appid = "42e9fa6517e72a0fffdea078b660659c" )
            call.enqueue(object: Callback<WeatherResponse>{
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.d("MainActivity","result :"+t.message)
                }

                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if(response.code() == 200){
                        val weatherResponse = response.body()
                        Log.d("MainActivity", "result: " + weatherResponse.toString())
                        //weather_test.text = weatherResponse!!.weather[0].main
                        //weather_test2.text = weatherResponse!!.weather[0].description
                        //weather_test3.text = weatherResponse!!.main?.temp.toString()
                       // weather_test4.text = weatherResponse!!.main?.humidity.toString()
                        //weather_test5.text = weatherResponse!!.main?.pressure.toString()
                    }
                }
            })
        }
        */
        //rv_menuList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        //rv_menuList.setHasFixedSize(true)
       // rv_menuList.adapter = MenuAdapter(menu_list)
    }
}

interface WeatherService{
    @GET("/data/2.5/weather")
    fun getCurrentWeatherData(
        @Query("q") q: String,
        @Query("appid") appid: String) :
            Call<WeatherResponse>
}

class WeatherResponse(){
    @SerializedName("weather") var weather = ArrayList<Weather>()
    @SerializedName("main") var main: Main? = null
}

class Weather {
    @SerializedName("id") var id: Int = 0
    @SerializedName("main") var main : String? = null
    @SerializedName("description") var description: String? = null
    @SerializedName("icon") var icon : String? = null
}

class Main {
    @SerializedName("temp")
    var temp: Float = 0.toFloat()
    @SerializedName("humidity")
    var humidity: Float = 0.toFloat()
    @SerializedName("pressure")
    var pressure: Float = 0.toFloat()
    @SerializedName("temp_min")
    var temp_min: Float = 0.toFloat()
    @SerializedName("temp_max")
    var temp_max: Float = 0.toFloat()

}

//qrder menu class
class QrderResponse(){
    @SerializedName("menu") var menu = ArrayList<qrder_menu>()
}

class qrder_menu {
    @SerializedName("placeId") var placeId: Int = 0
    @SerializedName("menuId") var menuId : Int = 0
    @SerializedName("menu") var menu: String? = null
    @SerializedName("price") var price : Int = 0
}


