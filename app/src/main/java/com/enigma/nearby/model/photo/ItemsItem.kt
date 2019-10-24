package com.enigma.nearby.model.photo

import com.google.gson.annotations.SerializedName

data class ItemsItem(

	@field:SerializedName("createdAt")
	val createdAt: Int? = null,

	@field:SerializedName("checkin")
	val checkin: Checkin? = null,

	@field:SerializedName("visibility")
	val visibility: String? = null,

	@field:SerializedName("prefix")
	val prefix: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("suffix")
	val suffix: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("height")
	val height: Int? = null
)