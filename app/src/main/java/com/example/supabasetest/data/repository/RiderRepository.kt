package com.example.supabasetest.data.repository

import com.example.supabasetest.data.datasource.RemoteRiderDataSource
import com.example.supabasetest.data.model.Resource
import com.example.supabasetest.data.model.Rider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RiderRepository @Inject constructor(
    private val riderDataSource: RemoteRiderDataSource
) {
    fun loadRiders(): Flow<Resource<List<Rider>>> = flow {
        emit(Resource.Loading)

        try {
            val result = riderDataSource.getAllRiders()
            emit(Resource.Success(result))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }
}