package com.paulr.prettysunny.presentation

import com.paulr.prettysunny.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo : WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
