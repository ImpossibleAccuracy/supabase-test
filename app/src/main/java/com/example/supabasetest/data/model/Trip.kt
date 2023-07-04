package com.example.supabasetest.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Trip(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val id: Int? = null,
    @JsonProperty("riders")
    val rider: Rider? = null,
    @JsonProperty("customers")
    val customer: Customer? = null,
    @JsonProperty("cars")
    val car: Car? = null,
    @JsonProperty("point_origin")
    val origin: String? = null,
    @JsonProperty("point_destination")
    val destination: String? = null,
    @JsonProperty("trip_state")
    val status: TripStatus? = null
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Trip

        if (id != other.id) return false
        if (rider != other.rider) return false
        if (customer != other.customer) return false
        if (car != other.car) return false
        if (origin != other.origin) return false
        if (destination != other.destination) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (rider?.hashCode() ?: 0)
        result = 31 * result + (customer?.hashCode() ?: 0)
        result = 31 * result + (car?.hashCode() ?: 0)
        result = 31 * result + (origin?.hashCode() ?: 0)
        result = 31 * result + (destination?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        return result
    }
}
