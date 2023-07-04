package com.example.supabasetest.ui.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabasetest.data.model.Resource
import com.example.supabasetest.data.repository.RiderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val riderRepository: RiderRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            riderRepository.loadRiders()
                .flowOn(Dispatchers.IO)
                .catch {
                    _uiState.update {
                        it.copy(hasNetworkError = true, isRidersLoading = false)
                    }
                }
                .collect { resource ->
                    _uiState.update {
                        when (resource) {
                            is Resource.Success -> it.copy(
                                isRidersLoading = false,
                                hasNetworkError = false,
                                riders = resource.data
                            )
                            is Resource.Error -> {
                                resource.error.printStackTrace()

                                it.copy(
                                    isRidersLoading = false,
                                    hasNetworkError = true
                                )
                            }
                            is Resource.Loading -> it.copy(
                                isRidersLoading = true,
                                hasNetworkError = false
                            )
                        }
                    }
                }
        }
    }
}