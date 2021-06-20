package com.example.weatherapp

import com.squareup.moshi.Json

class WeatherData {
    @Json(name = "coord")
    var coord: Coord? = null
    @Json(name = "weather")
    var weather: List<Weather>? = null
    @Json(name = "base")
    var base: String? = null
    @Json(name = "main")
    var main: Main? = null
    @Json(name = "visibility")
    var visibility: Int? = null
    @Json(name = "wind")
    var wind: Wind? = null
    @Json(name = "clouds")
    var clouds: Clouds? = null
    @Json(name = "dt")
    var dt: Int? = null
    @Json(name = "sys")
    var sys: Sys? = null
    @Json(name = "id")
    var id: Int? = null
    @Json(name = "name")
    var name: String? = null
    @Json(name = "cod")
    var cod: Int? = null
}

class Clouds {
    @Json(name = "all")
    var all: Int? = null

}

class Coord {
    @Json(name = "lon")
    var lon: Double? = null
    @Json(name = "lat")
    var lat: Double? = null

}

class Main {
    @Json(name = "temp")
    var temp: Double? = null
    @Json(name = "pressure")
    var pressure: Int? = null
    @Json(name = "humidity")
    var humidity: Int? = null
    @Json(name = "temp_min")
    var temp_min: Double? = null
    @Json(name = "temp_max")
    var temp_max: Double? = null

}

class Weather {
    @Json(name = "id")
    var id: Int? = null
    @Json(name = "main")
    var main: String? = null
    @Json(name = "description")
    var description: String? = null
    @Json(name = "icon")
    var icon: String? = null

}

class Sys {
    @Json(name = "type")
    var type: Int? = null
    @Json(name = "id")
    var id: Int? = null
    @Json(name = "message")
    var message: Double? = null
    @Json(name = "country")
    var country: String? = null
    @Json(name = "sunrise")
    var sunrise: Int? = null
    @Json(name = "sunset")
    var sunset: Int? = null

}

class Wind {
    @Json(name = "speed")
    var speed: Double? = null
    @Json(name = "deg")
    var deg: Int? = null

}