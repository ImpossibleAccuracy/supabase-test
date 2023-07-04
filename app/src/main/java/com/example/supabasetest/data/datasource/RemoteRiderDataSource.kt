package com.example.supabasetest.data.datasource

import com.example.supabasetest.data.model.Rider
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject

class RemoteRiderDataSource @Inject constructor(
    private val client: SupabaseClient
) {
    suspend fun getAllRiders(): List<Rider> {
        return client.postgrest["riders"].select().decodeList()
    }
}