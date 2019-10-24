package com.enigma.nearby.model.photo

import com.google.gson.annotations.SerializedName

data class Photos(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("dupesRemoved")
	val dupesRemoved: Int? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)