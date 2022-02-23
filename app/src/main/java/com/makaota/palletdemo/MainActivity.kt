package com.makaota.palletdemo

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clMain = findViewById<ConstraintLayout>(R.id.cl_main)
        val image = findViewById<ImageView>(R.id.iv_main)

        Glide.with(this)
            .load("https://images.pexels.com/photos/234510/pexels-photo-234510.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("TAG","Error loading image",e)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Palette.from(resource!!.toBitmap())
                        .generate { Palette ->
                            Palette?.let {
                                val intColor = it.vibrantSwatch?.rgb ?: 0
                                clMain.setBackgroundColor(intColor)
                            }
                        }
                    return false
                }

            }).into(image)
    }
}