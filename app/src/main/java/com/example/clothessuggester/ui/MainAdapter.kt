package com.example.clothessuggester.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clothessuggester.R
import com.example.clothessuggester.data.network.getDayName
import com.example.clothessuggester.databinding.WeatherItemBinding
import com.example.clothessuggester.data.model.Interval

class MainAdapter(private val listener: (item: Int) -> Unit) :
    ListAdapter<Interval, MainAdapter.ItemViewHolder>(DaysDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WeatherItemBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(listener, getItem(position))
    }


    inner class ItemViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: (item: Int) -> Unit, interval: Interval) {
            with(binding) {
                weatherDegreeMax.text = root.context.getString(
                    R.string.temperature_format,
                    interval.values.temperatureMax.toInt()
                )
                weatherDegreeMin.text = root.context.getString(
                    R.string.temperature_format,
                    interval.values.temperatureMin.toInt()
                )
                date.text = root.context.getString(
                    R.string.date_format,
                    getDayName(interval.startTime.substringBefore("T"), "EEE"),
                    interval.startTime.substring(5, 10)
                )
                weatherIcon.setImageResource(interval.weatherImageId)
                root.setOnClickListener { listener(interval.clothesImageId) }
            }
        }

    }

    class DaysDiffUtil() : DiffUtil.ItemCallback<Interval>() {
        override fun areItemsTheSame(oldItem: Interval, newItem: Interval): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Interval, newItem: Interval): Boolean {
            return oldItem == newItem
        }
    }
}