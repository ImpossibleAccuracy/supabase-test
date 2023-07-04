package com.example.supabasetest.ui.fragment.rider_trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabasetest.data.model.Resource
import com.example.supabasetest.data.model.Rider
import com.example.supabasetest.data.model.Trip
import com.example.supabasetest.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RiderTripsViewModel @Inject constructor(
    private val tripRepository: TripRepository
) : ViewModel() {
    private val _trips = MutableStateFlow<Resource<List<Trip>>>(Resource.Loading)
    val trips = _trips.asStateFlow()

    fun loadTrips(rider: Rider) {
        viewModelScope.launch {
            tripRepository.getTripsByRiderId(rider.id)
                .flowOn(Dispatchers.IO)
                .catch {
                    _trips.value = Resource.Error(it)
                }.collect {
                    _trips.value = it

                    if (it is Resource.Error) {
                        it.error.printStackTrace()
                    }
                }
        }
    }

    fun acceptTrip(trip: Trip) {
        viewModelScope.launch {
            tripRepository.setTripToAcceptedState(trip)
        }
    }
}