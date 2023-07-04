package com.example.supabasetest.ui.adapter

import android.content.Context
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.supabasetest.R
import com.example.supabasetest.data.model.Rider
import com.example.supabasetest.databinding.ItemRiderBinding

class RiderViewHolder(
    private val context: Context,
    private val binding: ItemRiderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(rider: Rider) {
        ViewCompat.setTransitionName(binding.root, "rider_${rider.id}")

        binding.title.text =
            context.getString(R.string.rider_name_format, rider.name, rider.surname)
    }

    fun setOnClickListener(action: (v: View) -> Unit) {
        binding.root.setOnClickListener(action)
    }
}