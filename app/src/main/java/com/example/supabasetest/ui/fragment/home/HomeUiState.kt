package com.example.supabasetest.ui.fragment.home

import com.example.supabasetest.data.model.Rider

data class HomeUiState(
    val riders: List<Rider> = listOf(),
    val isRidersLoading: Boolean = true,
    val hasNetworkError: Boolean = false
)
