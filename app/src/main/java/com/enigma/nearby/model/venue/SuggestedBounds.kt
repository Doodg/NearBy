package com.enigma.nearby.model.venue

import com.google.gson.annotations.SerializedName

data class SuggestedBounds(

    @field:SerializedName("sw")
	val sw: Sw? = null,

    @field:SerializedName("ne")
	val ne: Ne? = null
)