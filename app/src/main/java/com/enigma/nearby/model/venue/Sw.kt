package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class Sw(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)