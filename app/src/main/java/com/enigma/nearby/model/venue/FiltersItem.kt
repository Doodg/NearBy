package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class FiltersItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)