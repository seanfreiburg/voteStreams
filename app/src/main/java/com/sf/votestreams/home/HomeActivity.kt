package com.sf.votestreams.home

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
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

    @BindView(R.id.postId)
    lateinit var postId: TextView

    @BindView(R.id.userId)
    lateinit var userId: TextView

    @BindView(R.id.title)
    lateinit var title: TextView

    @BindView(R.id.body)
    lateinit var body: TextView

    @BindView(R.id.postImage)
    lateinit var postImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBasics()
        setupPostView()
    }

    private fun setupBasics() {
        // set content view
        setContentView(R.layout.big_post_layout)

        // dagger inject
        DaggerHomeInjector.create().injectIntoActivity(this)

        // bind to views
        ButterKnife.bind(this)
    }

    private fun setupPostView() {
        // load post if not loaded and not loading
        if (homeViewModel.isPostLoaded.value == false && homeViewModel.isLoadingPost.value == false) {
            homeViewModel.getPost()
        }

        homeViewModel.post.observe(this, updatePost())
    }

    private fun updatePost(): Observer<Post> {
        return Observer { post ->
            userId.text = post.userId.toString()
            postId.text = post.id.toString()
            title.text = post.title
            body.text = post.body
            Glide.with(this).load("https://i.imgur.com/XgbZdeA.jpg").centerInside().into(postImage)
        }
    }
}
