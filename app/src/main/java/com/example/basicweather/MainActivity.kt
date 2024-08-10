package com.example.basicweather

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.basicweather.databinding.ActivityMainBinding
import com.example.basicweather.model.ExampleJson2KtKotlin
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var fiveDay1List = mutableListOf<Forecast>()
    val LOGGING_TAG = "weatherDATA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      /*  thread {
            val weather = try {
                buildURLForWeather()?.readText()
            } catch (e: Exception) {
                return@thread
            }
            //runOnUiThread{binding.tvWeather.text=weather}
            // runOnUiThread{consumerJson(weather)}
            runOnUiThread { consumeJson(weather) }

        }*/

       /* binding.ivAccuweather.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://developer.accuweather.com/")
            )
            startActivity(intent)
        }*/
    }

   /* fun consumeJson(weatherJSON: String?) {
        if (weatherJSON != null) {
            val gson = Gson()
            val weatherData =
                gson.fromJson<ExampleJson2KtKotlin>(
                    weatherJSON,
                    ExampleJson2KtKotlin::class.java
                )
            for (forecast in weatherData.DailyForecasts) {
                binding.tvWeather.append(
                    "Date: " +
                            forecast.Date?.substring(0, 10) +
                            " Min: " +
                            forecast.Temperature?.Minimum?.Value +
                            " Max: " +
                            forecast.Temperature?.Maximum?.Value +
                            "\n"
                )
            }
        }



    }*/
}