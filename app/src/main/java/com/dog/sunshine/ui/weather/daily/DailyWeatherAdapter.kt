package com.dog.sunshine.ui.weather.daily

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.databinding.ItemWeatherDailyBinding

class DailyWeatherAdapter(
    private val listDaily: List<Current>,
    private val clickListener: (Current) -> Unit
): RecyclerView.Adapter<DailyWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemWeatherBinding: ItemWeatherDailyBinding = ItemWeatherDailyBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return DailyWeatherViewHolder(
            itemWeatherBinding
        )
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val current: Current = listDaily[position]
        holder.bindData(
            current,
            clickListener
        )
    }

    override fun getItemCount(): Int {
        return listDaily.size
    }
}

//package com.dog.sunshine.ui.weather.daily
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.paging.PagedListAdapter
//import androidx.recyclerview.widget.DiffUtil
//import com.dog.sunshine.data.weather.current.Current
//import com.dog.sunshine.databinding.ItemWeatherDailyBinding
//
//class DailyWeatherAdapter(
//    private val clickListener: (Current) -> Unit
//): PagedListAdapter<Current, DailyWeatherViewHolder>(
//    DIFF_CALLBACK
//) {
//
//    companion object {
//
//        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Current>() {
//            override fun areItemsTheSame(oldItem: Current, newItem: Current): Boolean {
//                return oldItem.date == newItem.date
//            }
//
//            override fun areContentsTheSame(oldItem: Current, newItem: Current): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val itemWeatherBinding: ItemWeatherDailyBinding = ItemWeatherDailyBinding.inflate(
//            layoutInflater,
//            parent,
//            false
//        )
//        return DailyWeatherViewHolder(
//            itemWeatherBinding
//        )
//    }
//
//    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
//        val current: Current? = getItem(position)
//        current?.let {
//            holder.bindData(
//                it,
//                clickListener
//            )
//        }
//    }
//}