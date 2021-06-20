package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var city: String = "mumbai"
    val api: String = "9354b1717f6d50e313d1f26370c908db"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<LinearLayout>(R.id.overviewContainer).visibility = View.GONE
        findViewById<LinearLayout>(R.id.detailsContainer).visibility = View.GONE
    }

    fun displayWeatherDetails(view: View){
        city = findViewById<TextView>(R.id.address).text.toString()

        findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
        findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE


        val retrofit = Api.buildService(DataService::class.java)
        retrofit.weatherInfo(city, "metric", api)?.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                val info = response?.body()

                if (info != null){
                    Log.i("Response", info.toString())

                    //Retrieve data from response body
                    val updatedAt: Long = info.dt!!.toLong()
                    val updatedAtText = "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))

                    val temp = info.main?.temp.toString()
                    val tempMin = info.main?.temp_min.toString()
                    val tempMax = info.main?.temp_max.toString()
                    val pressure = info.main?.pressure.toString()
                    val humidity = info.main?.humidity.toString()

                    val sunrise = info.sys?.sunrise?.toLong()
                    val sunset = info.sys?.sunset?.toLong()

                    val windSpeed = info.wind?.speed.toString()
                    val description = info.weather?.get(0)?.description

                    val address = info.name + ", " + info.sys?.country

                    //Populate the views

                    findViewById<TextView>(R.id.address).text = address
                    findViewById<TextView>(R.id.updatedAt).text = updatedAtText
                    findViewById<TextView>(R.id.status).text = description?.capitalize()
                    findViewById<TextView>(R.id.temp).text = temp+ "°C"
                    findViewById<TextView>(R.id.temp_min).text = "Min. " + tempMin + "°C"
                    findViewById<TextView>(R.id.temp_max).text = "Max. " + tempMax + "°C"

                    findViewById<TextView>(R.id.sunrise).text =
                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                            Date(
                                sunrise!!*1000
                            )
                        )

                    findViewById<TextView>(R.id.sunset).text =
                        SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                            Date(
                                sunset!!*1000
                            )
                        )

                    findViewById<TextView>(R.id.pressure).text = pressure
                    findViewById<TextView>(R.id.humidity).text = humidity
                    findViewById<TextView>(R.id.wind).text = windSpeed

                    findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                    findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
                    findViewById<LinearLayout>(R.id.overviewContainer).visibility = View.VISIBLE
                    findViewById<LinearLayout>(R.id.detailsContainer).visibility = View.VISIBLE

                } else{

                    findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                    findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
                    findViewById<Button>(R.id.reset).visibility = View.VISIBLE

                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                Log.i("Error", t.message.toString())
            }

        })
    }

    fun reset(view: View){
        findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
        findViewById<LinearLayout>(R.id.overviewContainer).visibility = View.GONE
        findViewById<LinearLayout>(R.id.detailsContainer).visibility = View.GONE
        findViewById<TextView>(R.id.errorText).visibility = View.GONE
        findViewById<Button>(R.id.reset).visibility = View.GONE
    }
}