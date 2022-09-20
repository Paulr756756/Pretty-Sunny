package com.paulr.prettysunny.data.mappers

import com.paulr.prettysunny.data.remote.WeatherDataDto
import com.paulr.prettysunny.data.remote.WeatherDto
import com.paulr.prettysunny.domain.weather.WeatherData
import com.paulr.prettysunny.domain.weather.WeatherInfo
import com.paulr.prettysunny.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class IndexedWeatherData(
    val index:Int,
    val data: WeatherData
)


fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed{ index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed= windSpeed,
                humidity= humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues{
        it.value.map{it.data}
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo{
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val curentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = curentWeatherData
    )

}
