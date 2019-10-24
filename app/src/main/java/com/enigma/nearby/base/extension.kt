package com.enigma.nearby.base

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.enigma.nearby.R

fun ImageView.loadImage(url: String) {
    Glide.with(this.context).load(url)
        .error(R.drawable.white_square)
        .centerCrop().into(this)
}


fun View.show() {
    this.visibility=View.VISIBLE
}
fun View.hide() {
    this.visibility=View.GONE
}