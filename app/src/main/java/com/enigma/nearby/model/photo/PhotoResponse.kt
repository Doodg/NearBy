package com.enigma.nearby.model.photo

import com.google.gson.annotations.SerializedName

data class PhotoResponse(

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("response")
	val response: Response? = null
)