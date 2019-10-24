package com.enigma.nearby.model.photo

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("photo")
	val photo: Photo? = null,

	@field:SerializedName("id")
	val id: String? = null
)