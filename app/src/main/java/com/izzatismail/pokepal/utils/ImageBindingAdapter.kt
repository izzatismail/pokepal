package com.izzatismail.pokepal.utils

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.izzatismail.pokepal.R

@BindingAdapter("bind:imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    var dominantColor: Int

    if (url.isNullOrEmpty()) {
        imageView.setBackgroundResource(carbon.R.drawable.carbon_iconplaceholder)
    } else {
        Glide.with(imageView.context)
            .load(url)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    val drawable = resource as BitmapDrawable
                    val bitmap = drawable.bitmap
                    Palette.Builder(bitmap).generate {
                        it?.let { palette ->
                            dominantColor = palette.getDominantColor(
                                ContextCompat.getColor(
                                    imageView.context,
                                    R.color.white
                                )
                            )

                            imageView.setBackgroundColor(dominantColor)


                        }
                    }

                    return false
                }

            })
            .into(imageView)
    }
}