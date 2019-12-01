package com.sf.votestreams.home

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.sf.votestreams.R
import com.sf.votestreams.home.dataModels.Post
import javax.inject.Inject


// this will display the view and send actions to the viewmodel

// mvvm
// rxjava
// dagger
// glide
// retrofit
// moshi

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @BindView(R.id.username)
    lateinit var username: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBasics()
        setupPostView()
    }

    private fun setupPostView() {
        // load post if not loaded and not loading
        if (homeViewModel.isPostLoaded.value == false && homeViewModel.isLoadingPost.value == false) {
            homeViewModel.getPost()
        }

        homeViewModel.post.observe(this, Observer<Post> { post ->
            username.text = post.title
        })

        //val imageView = findViewById<ImageView>(R.id.imageView)
        //Glide.with(this).load("https://i.imgur.com/XgbZdeA.jpg").centerInside().into(imageView)
    }

    private fun setupBasics() {
        // dagger inject
        DaggerHomeInjector.create().injectIntoActivity(this)

        // set content view
        setContentView(R.layout.big_post_layout)

        // bind to views
        ButterKnife.bind(this)
    }
}
