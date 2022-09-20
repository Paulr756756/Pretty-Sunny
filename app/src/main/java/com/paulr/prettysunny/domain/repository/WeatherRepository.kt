package com.paulr.prettysunny.domain.repository

import com.paulr.prettysunny.domain.util.Resource
import com.paulr.prettysunny.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long:Double): Resource<WeatherInfo>
}