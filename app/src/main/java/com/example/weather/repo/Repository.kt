package com.example.weather.repo

import com.example.weather.model.AllWeatherData
import com.example.weather.model.WeatherData

object Repository {
    private val apiservice=Network.getApiServices()

    suspend fun getWeatherDetails(lat:Double,lon:Double):WeatherData {
        return apiservice.getWeatherDetail(lat,lon,Network.apikey)
    }
    suspend fun getAllWeatherDetail(lat: Double,lon: Double):AllWeatherData{
        return apiservice.getAllDayWeatherDetail(lat,lon,"day",Network.apikey)
    }
}