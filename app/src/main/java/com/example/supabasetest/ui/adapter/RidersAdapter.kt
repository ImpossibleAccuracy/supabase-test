package com.example.supabasetest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.supabasetest.data.model.Rider
import com.example.supabasetest.databinding.ItemRiderBinding

class RidersAdapter(
    private val context: Context,
    private val onRiderClickListener: OnRiderClickListener
) : ListAdapter<Rider, RiderViewHolder>(RiderItemDiffCallback()) {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiderViewHolder {
        val binding = ItemRiderBinding.inflate(inflater, parent, false)
        return RiderViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: RiderViewHolder, position: Int) {
        val rider = getItem(position)
        holder.bind(rider)

        holder.setOnClickListener {
            onRiderClickListener.onItemClick(rider, position, it)
        }
    }
}

fun interface OnRiderClickListener {
    fun onItemClick(rider: Rider, position: Int, view: View)
}