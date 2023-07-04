package com.example.supabasetest.data.repository

import com.example.supabasetest.data.datasource.RemoteCustomerDataSource
import com.example.supabasetest.data.model.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val customerDataSource: RemoteCustomerDataSource
) {
    suspend fun createCustomer(name: String, surname: String): Customer =
        withContext(Dispatchers.IO) {
            customerDataSource.createCustomer(name, surname)
        }
}