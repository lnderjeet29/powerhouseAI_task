package com.example.weather.viewmodel

import android.location.Location
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
    val weatherList= MutableLiveData<List<WeatherData>>()


    init {
        getListofWheater()
    }

    fun getWeatherData(lat:Double,lon:Double){
        viewModelScope.launch {
            try {
                val weather=Repository.getWeatherDetails(lat,lon)
                WeatherDetail.postValue(weather)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
     fun getAllDayWeatherData(lat: Double,lon: Double){
         viewModelScope.launch {
             try {
                 val weather=Repository.getAllWeatherDetail(lat,lon)
                 AllWeatherDetail.postValue(weather)
             }catch (e:Exception){
                 e.printStackTrace()
             }
         }
     }
    fun getListofWheater(){
        val listOfCitiesLatLng = listOf( 40.7128 to 74.0060,28.7041 to 77.1025, 1.3521 to  103.8198,19.0760 to 72.8777)

        viewModelScope.launch {
            val weathersData = mutableListOf<WeatherData>()
            try {

                listOfCitiesLatLng.forEach {

                    val weather=Repository.getWeatherDetails(lat =it.first , lon = it.second )

                    weathersData.add(weather)
                }

                weatherList.postValue(weathersData)
            }catch (e:Exception){
                e.printStackTrace()
            }

        }
    }
}