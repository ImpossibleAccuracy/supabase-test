package com.example.supabasetest.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Customer(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val id: Int? = null,
    val name: String? = null,
    val surname: String? = null,
    @JsonProperty("birth_date")
    val birthDate: Date? = null
) : Parcelable