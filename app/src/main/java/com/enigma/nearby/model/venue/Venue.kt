package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class Venue(

    @field:SerializedName("hereNow")
	val hereNow: HereNow? = null,

    @field:SerializedName("stats")
	val stats: Stats? = null,

    @field:SerializedName("contact")
	val contact: Contact? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("verified")
	val verified: Boolean? = null,

    @field:SerializedName("location")
	val location: Location? = null,

    @field:SerializedName("id")
	val id: String? = null,

    @field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null,

    @field:SerializedName("photos")
	val photos: Photos? = null,

    @field:SerializedName("beenHere")
	val beenHere: BeenHere? = null
)