package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.AllWeatherData
import com.example.weather.model.WeatherData
import com.example.weather.repo.Repository
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel:ViewModel() {
    val WeatherDetail=MutableLiveData<WeatherData>()
    val AllWeatherDetail=MutableLiveData<AllWeatherData>()
    fun getWeatherData(lat:Float,lon:Float){
        viewModelScope.launch {
            try {
                val weather=Repository.getWeatherDetails(lat,lon)
                WeatherDetail.postValue(weather)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
     fun getAllDayWeatherData(lat: Float,lon: Float){
         viewModelScope.launch {
             try {
                 val weather=Repository.getAllWeatherDetail(lat,lon)
                 AllWeatherDetail.postValue(weather)
             }catch (e:Exception){
                 e.printStackTrace()
             }
         }
     }
}