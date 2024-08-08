package com.example.basicweather

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.basicweather.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    var fiveDay1List = mutableListOf<Forecast>()
    val LOGGING_TAG = "weatherDATA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         thread{
             val weather = try {
                 buildURLForWeather()?.readText()
             }catch (e: Exception){
                 return@thread
             }
             //runOnUiThread{binding.tvWeather.text=weather}
             runOnUiThread{consumerJson(weather)}

         }

        binding.ivAccuweather.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://developer.accuweather.com/")
            )
            startActivity(intent)
        }
    }
    fun consumerJson(weatherJSON:String?){
        if(fiveDay1List!=null){
            fiveDay1List.clear()
        }
        if (weatherJSON != null){
            try{
                //get the root JSON object
                val rootWeatherData = JSONObject(weatherJSON)

                //find the daily forecasts arrays
                val fiveDayForecast = rootWeatherData.getJSONArray("DailyForecasts")

                //get data from each entry in the array
                for(i in 0 until fiveDayForecast.length()){
                    val forecastObject = Forecast()
                    val dailyWeather = fiveDayForecast.getJSONObject(i)

                    //get date
                    val date = dailyWeather.getString("Date")
                    Log.i(LOGGING_TAG,"consumeJson: Date$date")
                    forecastObject.date = date

                    //get minimum temperature
                    val temperatureObject = dailyWeather.getJSONObject("Temperature")
                    val minTempObjects = temperatureObject.getJSONObject("Minimum")
                    val minTemp = minTempObjects.getString("Value")
                    Log.i(LOGGING_TAG,"consumeJson: minTemp$minTemp")
                    forecastObject.minimumTemperature = minTemp

                    //get maximum temperature
                    val maxTempObjects = temperatureObject.getJSONObject("Maximum")
                    val maxTemp = maxTempObjects.getString("Value")
                    Log.i(LOGGING_TAG,"consumeJson: maxTemp$maxTemp")
                    forecastObject.maximumTemperature = maxTemp
                    fiveDay1List.add(forecastObject)
                    binding.tvWeather.append(
                        "Date: $date Min: $minTemp Max:$maxTemp \n"
                    )

                }
            }catch (e: JSONException){
                e.printStackTrace()
            }
        }
    }
}