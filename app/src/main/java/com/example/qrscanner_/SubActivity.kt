package com.example.qrscanner_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sub.*

import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        if(intent.hasExtra("testurl")){
            test_Lbl.text = intent.getStringExtra("testurl")

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
                        weather_test.text = weatherResponse!!.weather[0].main
                    }
                }
            })
        }

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
}

class Weather {
    @SerializedName("id") var id: Int = 0
    @SerializedName("main") var main : String? = null
    @SerializedName("description") var description: String? = null
    @SerializedName("icon") var icon : String? = null
}