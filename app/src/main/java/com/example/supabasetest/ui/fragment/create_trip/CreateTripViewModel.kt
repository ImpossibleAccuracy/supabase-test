package com.example.supabasetest.ui.fragment.create_trip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabasetest.data.model.Resource
import com.example.supabasetest.data.model.Rider
import com.example.supabasetest.data.repository.CustomerRepository
import com.example.supabasetest.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTripViewModel @Inject constructor(
    private val customerRepository: CustomerRepository,
    private val tripRepository: TripRepository
) : ViewModel() {
    private val _creationState = MutableStateFlow<Resource<Boolean>?>(null)
    val creationState = _creationState.asStateFlow()

    fun createTripByCustomerDataAndPoints(
        rider: Rider,
        name: String,
        surname: String,
        origin: String,
        destination: String
    ) {
        viewModelScope.launch {
            val customer = customerRepository.createCustomer(name, surname)

            tripRepository.createTripTemplate(customer, rider, origin, destination)

            _creationState.value = Resource.Success(true)
        }
    }

    fun handleCreationResult() {
        _creationState.value = null
    }
}
