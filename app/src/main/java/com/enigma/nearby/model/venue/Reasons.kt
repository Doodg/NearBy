package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class Reasons(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)