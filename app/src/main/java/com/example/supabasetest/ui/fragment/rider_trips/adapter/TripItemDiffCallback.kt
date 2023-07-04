package com.example.supabasetest.ui.fragment.rider_trips.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.supabasetest.data.model.Trip

class TripItemDiffCallback : DiffUtil.ItemCallback<Trip>() {
    override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
        return oldItem == newItem
    }
}