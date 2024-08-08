package com.example.basicweather

class Forecast {
    var date: String = ""
    var minimumTemperature: String =""
    var maximumTemperature: String =""
    var fiveDay1List = mutableListOf<Forecast>()
    val LOGGING_TAG = "weatherDATA"

}