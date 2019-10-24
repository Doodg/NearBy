package com.enigma.nearby.model.venue

import com.enigma.nearby.model.venue.FiltersItem
import com.google.gson.annotations.SerializedName

data class SuggestedFilters(

	@field:SerializedName("header")
	val header: String? = null,

	@field:SerializedName("filters")
	val filters: List<FiltersItem?>? = null
)