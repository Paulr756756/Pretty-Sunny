package com.paulr.prettysunny.data.mappers

import com.paulr.prettysunny.data.remote.WeatherDataDto
import com.paulr.prettysunny.domain.weather.WeatherData
import com.paulr.prettysunny.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed{ index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = temperature,
            pressure = pressure,
            windSpeed= windSpeed,
            humidity= humidity,
            weatherType = WeatherType.fromWMO(weatherCode)
        )
    }.groupBy {
        it.time.dayOfMonth
    }
}
