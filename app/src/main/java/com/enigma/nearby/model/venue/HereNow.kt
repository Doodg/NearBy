package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class HereNow(

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("groups")
	val groups: List<Any?>? = null
)