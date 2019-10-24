package com.enigma.nearby.model.photo

import com.google.gson.annotations.SerializedName

data class Photo(

	@field:SerializedName("prefix")
	val prefix: String? = null,

	@field:SerializedName("suffix")
	val suffix: String? = null
)