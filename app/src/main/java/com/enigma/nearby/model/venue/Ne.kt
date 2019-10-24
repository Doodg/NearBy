package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class Ne(

	@field:SerializedName("lng")
	val lng: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)