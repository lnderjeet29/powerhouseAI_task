package com.example.weather.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weather.databinding.FragmentItemAllDayDataBinding
import com.example.weather.model.AllWeatherData
import com.example.weather.util.CheckForImage
import java.util.*

class ItemAdapter():ListAdapter<AllWeatherData.Hourly,ItemAdapter.viewholder>(HomeDiff) {

    private val calendar=Calendar.getInstance()
    object HomeDiff:DiffUtil.ItemCallback<AllWeatherData.Hourly>() {
        override fun areItemsTheSame(oldItem: AllWeatherData.Hourly, newItem: AllWeatherData.Hourly): Boolean {
            return oldItem.dt==newItem.dt
        }

        override fun areContentsTheSame(oldItem: AllWeatherData.Hourly, newItem: AllWeatherData.Hourly): Boolean {
            return oldItem==newItem
        }

    }

    inner class viewholder(val binding: FragmentItemAllDayDataBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
       return viewholder(FragmentItemAllDayDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val weather=getItem(position)
        holder.binding.apply {
            imgWeather.load(CheckForImage.checkImage(weather.weather[0].icon))
            txtWeather.text=weather.weather[0].main
            txtTime.text=getTime(position)
        }
    }

    private fun getTime(position: Int):String{

        var am_pm=""

        var time=calendar.get(Calendar.HOUR_OF_DAY)+position

        if (time>=24){
            time =time - 24;
        }

           if (time==0){
               time=12
               am_pm="AM"
           }else if (time>=12 ){
               am_pm=" PM"
               if(time>24){
               time=time-24
               }
               if(time>=12){
                   time=time-12
               }
               if (time==0)
                   time=12
           }else{
               am_pm=" AM"
           }

        return time.toString()+am_pm
    }
}