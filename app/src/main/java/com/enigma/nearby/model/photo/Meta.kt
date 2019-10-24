package com.enigma.nearby.model.photo

import com.google.gson.annotations.SerializedName

data class Meta(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("requestId")
	val requestId: String? = null
)