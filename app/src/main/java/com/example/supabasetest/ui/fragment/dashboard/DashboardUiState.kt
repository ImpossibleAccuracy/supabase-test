package com.example.supabasetest.ui.fragment.dashboard

import com.example.supabasetest.data.model.Rider

data class DashboardUiState(
    val riders: List<Rider> = listOf(),
    val isRidersLoading: Boolean = true,
    val hasNetworkError: Boolean = false
)
