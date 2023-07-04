package com.example.supabasetest.ui.fragment.rider_trips.adapter

import android.content.Context
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.supabasetest.R
import com.example.supabasetest.data.model.Trip
import com.example.supabasetest.databinding.ItemTripBinding

class TripViewHolder(
    private val context: Context,
    private val binding: ItemTripBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(trip: Trip) {
        ViewCompat.setTransitionName(binding.root, "trip_${trip.id}")

        trip.customer?.let {
            binding.customer.text =
                context.getString(
                    R.string.rider_name_format,
                    it.name,
                    it.surname
                )
        }

        binding.status.text = trip.status?.title
    }

    fun setOnClickListener(action: (v: View) -> Unit) {
        binding.root.setOnClickListener(action)
    }
}