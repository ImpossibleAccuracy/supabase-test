package com.example.supabasetest.data.datasource

import com.example.supabasetest.data.model.Customer
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import java.util.*
import javax.inject.Inject

class RemoteCustomerDataSource @Inject constructor(
    private val client: SupabaseClient
) {
    suspend fun createCustomer(name: String, surname: String): Customer {
        return client.postgrest["customers"].insert(
            Customer(name = name, surname = surname, birthDate = Date())
        ).decodeSingle()
    }
}