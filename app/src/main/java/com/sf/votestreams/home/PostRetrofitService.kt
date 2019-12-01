package com.sf.votestreams.home

import com.sf.votestreams.home.dataModels.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PostRetrofitService {
    @GET("/posts")
    fun getPosts(): Single<List<Post>>

    @GET("/posts/{postId}")
    fun getPost(@Path("postId") postId: Int): Single<Post>
}