package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class Stats(

	@field:SerializedName("visitsCount")
	val visitsCount: Int? = null,

	@field:SerializedName("checkinsCount")
	val checkinsCount: Int? = null,

	@field:SerializedName("usersCount")
	val usersCount: Int? = null,

	@field:SerializedName("tipCount")
	val tipCount: Int? = null
)