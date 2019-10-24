package com.enigma.nearby.model.photo

import com.google.gson.annotations.SerializedName

data class Source(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)