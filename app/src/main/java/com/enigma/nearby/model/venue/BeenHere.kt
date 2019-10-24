package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class BeenHere(

	@field:SerializedName("marked")
	val marked: Boolean? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("lastCheckinExpiredAt")
	val lastCheckinExpiredAt: Int? = null,

	@field:SerializedName("unconfirmedCount")
	val unconfirmedCount: Int? = null
)