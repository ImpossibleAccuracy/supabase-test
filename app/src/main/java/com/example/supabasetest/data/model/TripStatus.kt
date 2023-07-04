package com.example.supabasetest.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class TripStatus(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val id: Int,
    val title: String
) : Parcelable
