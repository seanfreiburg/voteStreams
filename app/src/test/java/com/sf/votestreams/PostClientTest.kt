package com.sf.votestreams

import com.sf.votestreams.home.PostClient
import com.sf.votestreams.home.PostRetrofitService
import com.sf.votestreams.home.dataModels.Post
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PostClientTest {

    @Mock
    private lateinit var mockPostRetrofitService: PostRetrofitService

    @Test
    fun getPosts_isCorrect() {
        Mockito.doReturn(Single.just(listOf(Post(1, 1, "title", "body"))))
            .`when`(mockPostRetrofitService).getPosts()

        PostClient(mockPostRetrofitService)
            .getPosts()
            .test()
            .assertValue( listOf(Post(1, 1, "title", "body")))
    }
}
