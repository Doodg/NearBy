package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class Location(

    @field:SerializedName("cc")
    val cc: String? = null,

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("labeledLatLngs")
    val labeledLatLngs: List<LabeledLatLngsItem?>? = null,

    @field:SerializedName("lng")
    val lng: Double? = null,

    @field:SerializedName("distance")
    val distance: Int? = null,

    @field:SerializedName("formattedAddress")
    val formattedAddress: List<String?>? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("state")
    val state: String? = null,

    @field:SerializedName("crossStreet")
    val crossStreet: String? = null,

    @field:SerializedName("lat")
    val lat: Double? = null,

    @field:SerializedName("neighborhood")
    val neighborhood: String? = null


) {
    fun formattedAddressCorrectly(): String {
        var currentAddress = ""
        formattedAddress?.forEach {
            currentAddress += it
        }
        return currentAddress
    }
}