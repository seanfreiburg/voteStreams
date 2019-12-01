package com.sf.votestreams.home

import com.sf.votestreams.home.dataModels.Post
import io.reactivex.Single
import javax.inject.Inject

// this will query the postClient and other data sources to get data
class HomeModel @Inject constructor(private val postClient: PostClient) {

    fun getPosts(): Single<List<Post>> {
        return postClient.getPosts()
    }

    fun getPost(postId: Int): Single<Post> {
        return postClient.getPost(postId)
    }

}