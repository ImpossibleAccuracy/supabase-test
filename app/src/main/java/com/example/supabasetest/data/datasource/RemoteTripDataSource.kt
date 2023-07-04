package com.example.supabasetest.data.datasource

import com.example.supabasetest.data.model.*
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject

class RemoteTripDataSource @Inject constructor(
    private val client: SupabaseClient
) {
    suspend fun createTrip(
        customer: Customer,
        rider: Rider,
        car: Car?,
        status: TripStatus,
        origin: String,
        destination: String
    ): Trip {
        val tripData = mapOf(
            "customer_id" to customer.id,
            "rider_id" to rider.id,
            "status_id" to status.id,
            "car_id" to car?.id,
            "point_origin" to origin,
            "point_destination" to destination
        )

        println(tripData)

        return client.postgrest["trips"].insert(tripData).decodeSingle()
    }

    suspend fun getTripStatusByName(name: String): TripStatus {
        return client.postgrest["trip_state"].select {
            eq("title", name)
        }.decodeSingle()
    }

    suspend fun getAllTrips(): List<Trip> =
        client.postgrest["trips"].select(
            columns = Columns.raw(
                """
                    *,
                    riders(*),
                    customers(*),
                    cars(*),
                    trip_state(*)
                """.trimIndent().replace("\n", "")
            )
        ).decodeList()

    suspend fun getTripsByRiderId(riderId: Int): List<Trip> =
        client.postgrest["trips"].select(
            columns = Columns.raw(
                """
                    *,
                    riders(*),
                    customers(*),
                    cars(*),
                    trip_state(*)
                """.trimIndent().replace("\n", "")
            )
        ) {
            eq("rider_id", riderId)
        }.decodeList()

    suspend fun setTripStatus(tripId: Int, statusId: Int) {
        val updateData = mapOf(
            "status_id" to statusId
        )

        client.postgrest["trips"].update(updateData) {
            eq("id", tripId)
        }
    }
}