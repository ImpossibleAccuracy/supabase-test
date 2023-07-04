package com.example.supabasetest.ui.fragment.rider_trips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.supabasetest.data.model.Trip
import com.example.supabasetest.databinding.ItemTripBinding

class TripAdapter(
    private val context: Context,
    private val onTripClickListener: OnTripClickListener
) : ListAdapter<Trip, TripViewHolder>(TripItemDiffCallback()) {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val binding = ItemTripBinding.inflate(inflater, parent, false)
        return TripViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val trip = getItem(position)
        holder.bind(trip)

        holder.setOnClickListener {
            onTripClickListener.onItemClick(trip, position, it)
        }
    }
}

fun interface OnTripClickListener {
    fun onItemClick(trip: Trip, position: Int, view: View)
}