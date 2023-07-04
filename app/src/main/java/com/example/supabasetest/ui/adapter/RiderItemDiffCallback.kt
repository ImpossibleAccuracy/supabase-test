package com.example.supabasetest.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.supabasetest.data.model.Rider

class RiderItemDiffCallback : DiffUtil.ItemCallback<Rider>() {
    override fun areItemsTheSame(oldItem: Rider, newItem: Rider): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Rider, newItem: Rider): Boolean {
        return oldItem == newItem
    }
}