package com.enigma.nearby.model.photo

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("photos")
	val photos: Photos? = null
)