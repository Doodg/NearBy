package com.enigma.nearby.model.photo

import com.google.gson.annotations.SerializedName

data class Checkin(

	@field:SerializedName("createdAt")
	val createdAt: Int? = null,

	@field:SerializedName("timeZoneOffset")
	val timeZoneOffset: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)