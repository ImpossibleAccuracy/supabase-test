package com.example.supabasetest.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Car(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val id: Int,
    val name: String,
    @JsonProperty("model_name")
    val model: String,
    val color: String,
) : Parcelable
