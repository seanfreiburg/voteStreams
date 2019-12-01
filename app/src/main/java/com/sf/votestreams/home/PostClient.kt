package com.sf.votestreams.home

import com.sf.votestreams.home.dataModels.Post
import io.reactivex.Single
import javax.inject.Inject


interface PostClientInterface {
    fun getPosts(): Single<List<Post>>

    fun getPost(postId: Int): Single<Post>
}

class PostClient @Inject constructor(private val postRetrofitService: PostRetrofitService) :
    PostClientInterface {
    override fun getPosts(): Single<List<Post>> {
        return postRetrofitService.getPosts()
    }

    override fun getPost(postId: Int): Single<Post> {
        return postRetrofitService.getPost(postId)
    }
}