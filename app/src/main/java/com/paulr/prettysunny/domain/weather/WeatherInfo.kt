package com.paulr.prettysunny.domain.weather

data class WeatherInfo (
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)