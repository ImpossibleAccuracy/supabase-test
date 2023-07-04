package com.example.supabasetest.data.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Rider(
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    val id: Int,
    val name: String,
    val surname: String,
    @JsonProperty("birth_date")
    val birthDate: Date
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rider

        if (id != other.id) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (birthDate != other.birthDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + birthDate.hashCode()
        return result
    }
}
