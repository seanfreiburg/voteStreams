package com.sf.votestreams.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sf.votestreams.R

// this will display the view and send actions to the viewmodel

// mvvm
// databinding
// rxjava
// dagger
// glide
// retrofit
// moshi

class HomeActivity : AppCompatActivity() {
    private val homeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load("https://i.imgur.com/XgbZdeA.jpg").centerInside().into(imageView)
    }
}
