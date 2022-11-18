package com.example.demo_app.data

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.demo_app.R
import java.io.File

@BindingAdapter("imageUrl", "withHolder", requireAll = false)
fun setImageUrl(imageView: ImageView, imageUrl: Any?, withHolder: Boolean = true) {
    try {
        imageUrl?.let {
            val source = when (it) {
                is File -> {
                    it
                }
                is Uri -> {
                    it
                }
                is Int -> {
                    it
                }
                is Drawable -> {
                    it
                }
                else -> {
                    it as String
                }
            }
            if (withHolder) {
                Glide.with(imageView)
                    .load(source)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(imageView).clearOnDetach()
            } else {
                Glide.with(imageView)
                    .load(source)
                    .into(imageView).clearOnDetach()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}