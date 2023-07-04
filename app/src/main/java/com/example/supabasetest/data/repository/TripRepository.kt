package com.example.supabasetest.data.repository

import com.example.supabasetest.data.datasource.RemoteTripDataSource
import com.example.supabasetest.data.model.Customer
import com.example.supabasetest.data.model.Resource
import com.example.supabasetest.data.model.Rider
import com.example.supabasetest.data.model.Trip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TripRepository @Inject constructor(
    private val tripDataSource: RemoteTripDataSource
) {
    suspend fun createTripTemplate(
        customer: Customer,
        rider: Rider,
        origin: String,
        destination: String
    ) =
        withContext(Dispatchers.IO) {
            val status = tripDataSource.getTripStatusByName("not_accepted")
            tripDataSource.createTrip(customer, rider, null, status, origin, destination)
        }

    fun getAllTrips(): Flow<Resource<List<Trip>>> = flow {
        emit(Resource.Loading)

        try {
            val result = tripDataSource.getAllTrips()
            emit(Resource.Success(result))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    fun getTripsByRiderId(riderId: Int): Flow<Resource<List<Trip>>> = flow {
        emit(Resource.Loading)

        try {
            val result = tripDataSource.getTripsByRiderId(riderId)
            emit(Resource.Success(result))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    suspend fun setTripToAcceptedState(trip: Trip) =
        withContext(Dispatchers.IO) {
            val status = tripDataSource.getTripStatusByName("accepted")

            tripDataSource.setTripStatus(trip.id!!, status.id)
        }
}